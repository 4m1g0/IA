package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import es.udc.ws.util.exceptions.InputValidationException;

public class ServiceUtil {
	public static final String DATE_FORMAT_MIN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DAY = "yyyy-MM-dd HH:mm:ss";
	
	public static Calendar getCalendar(String date) throws InputValidationException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_MIN);
		try {
			cal.setTime(sdf.parse(date));
		} catch (Exception e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		
		return cal;
	}
	
	public static String getDateString(Calendar cal) {
		SimpleDateFormat format = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_MIN);
		return format.format(cal.getTime());
	}

	private static List<MediaType> responseMediaTypes = Arrays
			.asList(new MediaType[] { MediaType.APPLICATION_JSON_TYPE,
					MediaType.APPLICATION_XML_TYPE });

	public static String getTypeAsStringFromHeaders(HttpHeaders headers) {
		List<MediaType> mediaTypes = headers.getAcceptableMediaTypes();
		for (MediaType m : mediaTypes) {
			MediaType compatibleType = getCompatibleAcceptableMediaType(m);
			if (compatibleType != null) {
				return compatibleType.toString();
			}
		}
		return null;
	}

	private static MediaType getCompatibleAcceptableMediaType(MediaType type) {
		for (MediaType m : responseMediaTypes) {
			if (m.isCompatible(type)) {
				return m;
			}
		}
		return null;
	}

	public static Link getLinkFromUri(URI baseUri, Class<?> resourceClass,
			Object instanceId, String rel, String title, String type) {
		Link.Builder linkBuilder = Link
				.fromPath(baseUri.toString()
								+ UriBuilder.fromResource(resourceClass)
										.build().toString().substring(1) + "/"
								+ instanceId).rel(rel).title(title);
		if (type != null) {
			linkBuilder.type(type);
		}
		return linkBuilder.build();
	}
	
	public static Link getIntervalLink(UriInfo uriInfo, String keyword,
			int startIndex, int count, String rel, String title, String type) {
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
				.queryParam("keyword", keyword)
				.queryParam("startIndex", startIndex)
				.queryParam("count", count);
		Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder)
				.rel(rel)
				.title(title);
		if (type!=null) {
			linkBuilder.type(type);
		}
		return linkBuilder.build();
	}

}
