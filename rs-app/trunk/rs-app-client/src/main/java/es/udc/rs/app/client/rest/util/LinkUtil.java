package es.udc.rs.app.client.rest.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;

import es.udc.rs.app.client.service.rest.dto.JaxbLink;


public class LinkUtil {

	public static URI getLinkUriFromList(List<JaxbLink> links, String rel) {
		String url = null;
		for (int i = 0; i < links.size() && url == null; i++) {
			JaxbLink l = links.get(i);
			String linkRel = l.getOtherAttributes().get(new QName("rel"));
			if (linkRel != null && linkRel.equals(rel)) {
				url = l.getHref();
			}
		}
		URI uri = null;
		if (url != null) {
			try {
				uri = new URI(url);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return uri;
	}

	public static URI getLinkUri(JaxbLink link) {
		String url = null;
		if (link != null) {
			url = link.getHref();
		}
		URI uri = null;
		if (url != null) {
			try {
				uri = new URI(url);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return uri;
	}

	public static URI getHeaderLinkUri(Response r, String linkRel) {
		URI linkUri = null;
		Link link = r.getLink(linkRel);
		if (link != null) {
			linkUri = link.getUri();
		}
		return linkUri;
	}

}
