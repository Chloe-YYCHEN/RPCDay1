package client;

import common.RPCRequest;
import common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Pass in the class object of the Service interface,
 * which is packaged by reflection as a request
 * jdk dynamic proxy, every time a proxy object calls a method,
 * it will be enhanced by this method (reflection to get the request object,
 * socket sent to the client)
 */
@AllArgsConstructor
public class RPCClientProxy implements InvocationHandler {

    private String host;

    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //构建request，使用lombok中的builder
        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                                    .methodName(method.getName())
                .params(args).paramsTypes(method.getParameterTypes()).build();
        //数据传输
        RPCResponse response = IOClient.sendRequest(host,port, request);
        return response.getData();
    }
    <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
