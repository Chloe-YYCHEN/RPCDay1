package server;

import java.util.HashMap;
import java.util.Map;

/**
 * Store the service interface name and the corresponding implementation class on the server side
 * Expose the relevant implementation classes when the service is started
 * Call the relevant implementation class on the server side according to the interface in the request
 * An implementation class may implement multiple interfaces
 */
public class ServiceProvider {
    private Map<String,Object> interfaceProvider;

    public ServiceProvider(){
        this.interfaceProvider = new HashMap<>();
    }
    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class clazs : interfaces){
            interfaceProvider.put(clazs.getName(),service);
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
