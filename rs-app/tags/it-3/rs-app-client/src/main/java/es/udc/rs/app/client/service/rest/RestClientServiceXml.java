package es.udc.rs.app.client.service.rest;

import javax.ws.rs.core.MediaType;

public class RestClientServiceXml extends RestClientService{
	
	@Override
	protected MediaType getMediaType() {
		return MediaType.APPLICATION_XML_TYPE;
	}

}
