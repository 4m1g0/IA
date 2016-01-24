package es.udc.rs.app.json;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

@Provider
public class JsonMoxyConfigurationContextResolver implements
		ContextResolver<MoxyJsonConfig> {

	private final MoxyJsonConfig config;

	public JsonMoxyConfigurationContextResolver() {
		final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
		namespacePrefixMapper.put("http://rs.udc.es/clients/xml", "clients");
		namespacePrefixMapper.put("http://rs.udc.es/calls/xml", "calls");

		config = new MoxyJsonConfig()
				.setNamespacePrefixMapper(namespacePrefixMapper)
				.setNamespaceSeparator('.').setIncludeRoot(true)
				.setFormattedOutput(true).setAttributePrefix("@");
	}

	@Override
	public MoxyJsonConfig getContext(Class<?> objectType) {
		return config;
	}
}