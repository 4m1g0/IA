package es.udc.rs.app.client.rest.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;


public class LinkUtil {

	public static URI getHeaderLinkUri(Response r, String linkRel) {
		URI linkUri = null;
		Link link = r.getLink(linkRel);
		if (link != null) {
			linkUri = link.getUri();
		}
		return linkUri;
	}

}
