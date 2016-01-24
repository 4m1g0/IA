package es.udc.rs.app.client.service.rest;

import javax.ws.rs.core.MediaType;

public class RestClientServiceJson extends RestClientService{
	
	@Override
	protected MediaType getMediaType() {
		return MediaType.APPLICATION_JSON_TYPE;
	}


}
