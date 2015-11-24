package es.udc.rs.app.client.service;

import es.udc.rs.app.configuration.ConfigurationParametersManager;

public class ClientServiceFactory {
	private final static String CLASS_NAME_PARAMETER = 
            "ClientMovieServiceFactory.className";
    private static Class<ClientService> serviceClass = null;

    private ClientServiceFactory() {
    }

    @SuppressWarnings("unchecked")
    private synchronized static Class<ClientService> getServiceClass() {

        if (serviceClass == null) {
            try {
                String serviceClassName = ConfigurationParametersManager
                        .getParameter(CLASS_NAME_PARAMETER);
                serviceClass = (Class<ClientService>) 
                        Class.forName(serviceClassName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return serviceClass;

    }

    public static ClientService getService() {

        try {
            return (ClientService) getServiceClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
