package es.udc.rs.app.client.service.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallListIntervalDto;
import es.udc.rs.app.client.dto.ClientDetailsDto;
import es.udc.rs.app.client.dto.ClientListIntervalDto;
import es.udc.rs.app.client.rest.util.CallToCallDtoJaxbConversor;
import es.udc.rs.app.client.rest.util.ClientDtoToClientDtoJaxbConversor;
import es.udc.rs.app.client.rest.util.JaxbExceptionConversor;
import es.udc.rs.app.client.rest.util.LinkUtil;
import es.udc.rs.app.client.service.ClientService;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.CallStateExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.ClientDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.ClientDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.InputValidationExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.InstanceNotFoundExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.RemoveClientExceptionDtoJaxb;
import es.udc.rs.app.configuration.ConfigurationParametersManager;
import es.udc.rs.app.constants.EnumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.jaxb.StringToDate;
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
	
	
	@Override
	public ClientDetailsDto addClient(ClientDetailsDto client) throws InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("clients");
		Response response = wt
				.request()
				.accept(this.getMediaType())
				.post(Entity.entity(
						new GenericEntity<JAXBElement<ClientDetailsDtoJaxb>>(
								ClientDtoToClientDtoJaxbConversor.toJaxbClientDetails(client)) {
							}, this.getMediaType()));
		try {
			validateResponse(Response.Status.CREATED.getStatusCode(), response);
			ClientDetailsDtoJaxb resultClient = response.readEntity(ClientDetailsDtoJaxb.class);
			return ClientDtoToClientDtoJaxbConversor.toClientDetailsDto(resultClient);
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
	public void updateClient(ClientDetailsDto client) throws InputValidationException,
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
						new GenericEntity<JAXBElement<ClientDetailsDtoJaxb>>(
								ClientDtoToClientDtoJaxbConversor.toJaxbClientDetails(client)) {}, this.getMediaType()));
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
	public ClientDetailsDto findClient(Long clientId) throws InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("clients/{id}")
				.resolveTemplate("id", clientId);
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			System.out.println(response.getStatus());
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDetailsDtoJaxb client = response.readEntity(ClientDetailsDtoJaxb.class);
			
			return ClientDtoToClientDtoJaxbConversor.toClientDetailsDto(client);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public ClientDetailsDto findClient(String DNI) throws InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("clients")
				.queryParam("dni", DNI);
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDetailsDtoJaxb client = response.readEntity(ClientDetailsDtoJaxb.class);
			
			return ClientDtoToClientDtoJaxbConversor.toClientDetailsDto(client);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public ClientListIntervalDto findClients(String keywords, int index, int numRows) {
		WebTarget wt = getEndpointWebTarget().path("clients")
				.queryParam("keywords", keywords)
				.queryParam("index", index)
				.queryParam("numRows", numRows);
		
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			ClientDtoJaxbList clients = response.readEntity(ClientDtoJaxbList.class);
			
			return new ClientListIntervalDto(
					ClientDtoToClientDtoJaxbConversor.toClientDtos(clients),
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
	public Long makeCall(Long clientId, String date, Integer duration, String type, String destPhone) throws InstanceNotFoundException, InputValidationException {
		WebTarget wt = getEndpointWebTarget().path("calls");
		
		CallDetailsDto call = new CallDetailsDto();
		call.setDateCall(StringToDate.getCalendar(date));
		call.setClientId(clientId);
		call.setDuration(duration);
		call.setType(EnumType.toEnumType(type));
		call.setDestPhone(destPhone);

		Response response = wt.request().accept(this.getMediaType())
				.post(Entity.entity(
						new GenericEntity<JAXBElement<CallDetailsDtoJaxb>>(CallToCallDtoJaxbConversor.toJaxbCallDetails(call)) {}, this.getMediaType()));
		
		try {
			validateResponse(Response.Status.CREATED.getStatusCode(), response);
			CallDetailsDtoJaxb callDetails = response.readEntity(CallDetailsDtoJaxb.class);
			return callDetails.getCallId();
		} catch (InputValidationException | InstanceNotFoundException ex) {
			throw new RuntimeException(ex);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	@Override
	public void changeCallState(Long clientId, String month, String year, String state) throws CallStateException, InstanceNotFoundException, MonthExpirationException {
		WebTarget wt = getEndpointWebTarget().path("calls/state")
				.queryParam("id", clientId)
				.queryParam("month", month)
				.queryParam("year", year)
				.queryParam("state", state);
				Response response = wt.request().accept(this.getMediaType()).post(null);
		try {
			System.out.println(response.getStatus());
			validateResponse(Response.Status.OK.getStatusCode(),
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
	public List<CallDetailsDto> findCallsToBill(Long clientId,  String month, String year) throws CallStateException, InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("calls")
				.queryParam("id", clientId)
				.queryParam("month", month)
				.queryParam(("year"), year);
				
		Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			CallDetailsDtoJaxbList calls = response.readEntity(CallDetailsDtoJaxbList.class);
			return CallToCallDtoJaxbConversor.toCallDetailsDtos(calls);
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
	public CallListIntervalDto findCalls(Long clientId, String initDate,
			String endDate, int index, int numRows)
			throws InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("calls")
				.resolveTemplate("id", clientId)
				.queryParam("initDate", initDate)
				.queryParam("endDate", endDate)
				.queryParam("index", index)
				.queryParam("numRows", numRows);
				
				Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			CallDtoJaxbList calls = response.readEntity(CallDtoJaxbList.class);
			return new CallListIntervalDto(
					CallToCallDtoJaxbConversor.toCallDtos(calls),
					LinkUtil.getHeaderLinkUri(response, "next"),
					LinkUtil.getHeaderLinkUri(response, "previous"));
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
	public CallListIntervalDto findCalls(Long clientId, String initDate,
			String endDate, int index, int numRows, String type)
			throws CallStateException, InstanceNotFoundException {
		WebTarget wt = getEndpointWebTarget().path("calls")
				.resolveTemplate("id", clientId)
				.queryParam("initDate", initDate)
				.queryParam("endDate", endDate)
				.queryParam("index", index)
				.queryParam("numRows", numRows)
				.queryParam("type", type);
				
				Response response = wt.request().accept(this.getMediaType()).get();
		try {
			validateResponse(Response.Status.OK.getStatusCode(), response);
			CallDtoJaxbList calls = response.readEntity(CallDtoJaxbList.class);
			return new CallListIntervalDto(
					CallToCallDtoJaxbConversor.toCallDtos(calls),
					LinkUtil.getHeaderLinkUri(response, "next"),
					LinkUtil.getHeaderLinkUri(response, "previous"));
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
	
	private void validateResponse(int expectedStatusCode, Response response)
			throws InstanceNotFoundException, CallStateException,
			InputValidationException, RemoveClientException {

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
		case CONFLICT: {
			CallStateExceptionDtoJaxb exDto = response
					.readEntity(CallStateExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toCallStatException(exDto);
		}
		case FORBIDDEN: {
			RemoveClientExceptionDtoJaxb exDto = response
					.readEntity(RemoveClientExceptionDtoJaxb.class);
			throw JaxbExceptionConversor.toRemoveClientException(exDto);
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
