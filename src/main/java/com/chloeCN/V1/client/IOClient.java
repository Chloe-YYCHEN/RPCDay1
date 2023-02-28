package com.chloeCN.V1.client;

import com.chloeCN.V1.common.RPCRequest;
import com.chloeCN.V1.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * client raise a request, Socket build connect and raise request then get Response
 * Here the request is packaged, different service needs different packaged
 * client only know Service Interface, Need a layer of dynamic proxy to encapsulate different services based on reflection
 * Dynamic Proxy 动态代理
 */
public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) throws IOException, ClassNotFoundException {
        try {
            Socket socket = new Socket(host,port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);

            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse) objectInputStream.readObject();

            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
            return null;
        }
    }
}
