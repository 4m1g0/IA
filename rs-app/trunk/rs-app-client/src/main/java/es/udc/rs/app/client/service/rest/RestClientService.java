package es.udc.rs.app.client.service.rest;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.dto.ClientDto;
import es.udc.rs.app.client.dto.ClientListIntervalDto;
import es.udc.rs.app.client.service.ClientService;
import es.udc.rs.app.configuration.ConfigurationParametersManager;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.json.JsonMoxyConfigurationContextResolver;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;



public abstract class RestClientService implements ClientService {
	private static javax.ws.rs.client.Client client = null;

	private final static String ENDPOINT_ADDRESS_PARAMETER = "RestClientService.endpointAddress";
	private WebTarget endPointWebTarget = null;
	
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
			client.register(JsonMoxyConfigurationContextResolver.class);
		}
		return client;
	}

	private WebTarget getEndpointWebTarget() {
		if (endPointWebTarget == null) {
			endPointWebTarget = getClient().target(
					ConfigurationParametersManager
							.getParameter(ENDPOINT_ADDRESS_PARAMETER));
		}
		return endPointWebTarget;
	}

	protected abstract MediaType getMediaType();
	
	private ClientListIntervalDto findProducts(WebTarget wt, MediaType type) {
		Response response = (type != null) ? wt.request().accept(type).get()
				: wt.request().get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ProductDtoJaxbList clients = response
					.readEntity(ProductDtoJaxbList.class);

			return new ClientListIntervalDto(
					ClientToClientDtoJaxbConversor.toClients(clients),
					LinkUtil.getHeaderLinkUri(response, "next"),
					LinkUtil.getHeaderLinkUri(response, "previous"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	
	@Override
	public ClientDto addClient(ClientDto client) throws InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("clients");
		Response response = wt
				.request()
				.accept(this.getMediaType())
				.post(Entity.entity(
						new GenericEntity<JAXBElement<ClientDtoJaxb>>(
								ClientDtoToClientDtoJaxbConversor.toJaxbClient(client)) {}, this.getMediaType())
								);
		try {
			validateResponse(Response.Status.CREATED.getStatusCode(), response);
			ClientDtoJaxb resultClient = response.readEntity(ClientDtoJaxb.class);
			return resultClient.getClientId();
		} catch (InputValidationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public void removeClient(Long clientId) throws InstanceNotFoundException,
			RemoveClientException {
		WebTarget wt = getEndpointWebTarget().path("clients/{id}")
				.resolveTemplate("id", clientId);
		Response response = wt.request().accept(this.getMediaType()).delete();
		try {
			validateResponse(Response.Status.NO_CONTENT.getStatusCode(),
					response);
		} catch (InstanceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public void updateClient(ClientDto client) throws InputValidationException,
			InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("clients/{id}")
				.resolveTemplate("id", client.getClientId());
		Response response = wt
				.request()
				.accept(this.getMediaType())
				.put(Entity.entity(
				// ClientDtoToClientDtoJaxbConversor.toJaxbClient(client),
				// this.getMediaType()));
				// Necessary to run with JSON and MOXy
						new GenericEntity<JAXBElement<ClientDtoJaxb>>(
								ClientDtoToClientDtoJaxbConversor
										.toJaxbClient(client)) {
						}, this.getMediaType()));
		try {
			validateResponse(Response.Status.NO_CONTENT.getStatusCode(),
					response);
		} catch (InputValidationException | InstanceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public ClientDto findClient(Long clientId) throws InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("clients/{id}")
				.resolveTemplate("id", clientId);
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDtoJaxb client = response.readEntity(ClientDtoJaxb.class);
			
			return ClientDtoToClientDtoJaxbConversor.toClientDtos(client);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public ClientDto findClient(String DNI) throws InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("clients")
				.queryParam("dni", DNI);
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDtoJaxb client = response.readEntity(ClientDtoJaxb.class);
			
			return ClientDtoToClientDtoJaxbConversor.toClientDtos(client);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public List<ClientDto> findClients(String keywords, int index, int numRows) {
		WebTarget wt = getEndpointWebTarget().path("clients")
				.queryParam("keywords", keywords)
				.queryParam("index", index)
				.queryParam("numRows", numRows);
		
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDtoJaxbList clients = response.readEntity(ClientDtoJaxbList.class);
			return ClientDtoToClientDtoJaxbConversor.toClientDtos(clients);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public void makeCall(Long clientId, Calendar date, Integer duration, enumType type, String destPhone) throws InstanceNotFoundException, InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("calls");
		
		CallDto call = new CallDto();
		call.setDateCall(date);
		call.setClientId(clientId);
		call.setDuration(duration);
		call.setType(type);
		call.setDestPhone(destPhone);

		Response response = wt.request().accept(this.getMediaType())
				.post(Entity.entity(
						new GenericEntity<JAXBElement<CallDtoJaxb>>(CallDtoToClalDtoJaxbConversor.toJaxbCall(call)) {}, this.getMediaType()));
		
		try {
			validateResponse(Response.Status.CREATED.getStatusCode(), response);
			CallDtoJaxb call = response.readEntity(CallDtoJaxb.class);
			return call.getCallId();
		} catch (InputValidationException | InstanceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public void changeCallState(Long clientId, Calendar date, enumState state) throws CallStateException, InstanceNotFoundException, MonthExpirationException {
		WebTarget wt = getEndpointWebTarget().path("clients/{id}")
				.resolveTemplate("id", clientId)
				.queryParam("month", date.get(Calendar.MONTH))
				.queryParam("year", date.get(Calendar.YEAR))
				.queryParam("state", state.toString());
				Response response = wt.request().accept(this.getMediaType()).put(null);
		try {
			validateResponse(Response.Status.NO_CONTENT.getStatusCode(),
					response);
		} catch (InstanceNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public List<CallDto> findCalls(Long clientId, Calendar month, int index,
			int numRows) throws CallStateException, InstanceNotFoundException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	@Override
	public List<CallDto> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, int index, int numRows)
			throws InstanceNotFoundException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	@Override
	public List<CallDto> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, int index, int numRows, enumType type)
			throws CallStateException, InstanceNotFoundException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	@Override
	public String getClientUrl(Long saleId) throws InstanceNotFoundException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	private void validateResponse(int expectedStatusCode, Response response)
			throws InstanceNotFoundException, CallStateException,
			InputValidationException {

		Response.Status statusCode = Response.Status.fromStatusCode(response
				.getStatus());
		String contentType = response.getMediaType() != null ? response
				.getMediaType().toString() : null;
		boolean expectedContentType = this.getMediaType().toString()
				.equalsIgnoreCase(contentType);
		if (!expectedContentType
				&& statusCode.getStatusCode() >= Response.Status.BAD_REQUEST
						.getStatusCode()) {
			throw new RuntimeException("HTTP error; status code = "
					+ statusCode);
		}
		switch (statusCode) {
		case NOT_FOUND: {
			InstanceNotFoundExceptionDtoJaxb exDto = response
					.readEntity(InstanceNotFoundExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toInstanceNotFoundException(exDto);
		}
		case BAD_REQUEST: {
			InputValidationExceptionDtoJaxb exDto = response
					.readEntity(InputValidationExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toInputValidationException(exDto);
		}
		case GONE: {
			SaleExpirationExceptionDtoJaxb exDto = response
					.readEntity(SaleExpirationExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toSaleExpirationException(exDto);
		}
		default:
			if (statusCode.getStatusCode() != expectedStatusCode) {
				throw new RuntimeException("HTTP error; status code = "
						+ statusCode);
			}
			break;
		}
	}
}
