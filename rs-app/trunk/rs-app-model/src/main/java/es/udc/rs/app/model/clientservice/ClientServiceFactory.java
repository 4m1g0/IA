package es.udc.rs.app.model.clientservice;

import es.udc.rs.app.configuration.ConfigurationParametersManager;




public class ClientServiceFactory {
	private final static String CLASS_NAME_PARAMETER = "ClientServiceFactory.className";
    private static ClientService service = null;

    private ClientServiceFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static ClientService getInstance() {
        try {
            String serviceClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            Class serviceClass = Class.forName(serviceClassName);
            return (ClientService) serviceClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static ClientService getService() {

        if (service == null) {
            service = getInstance();
        }
        return service;

    }
}
