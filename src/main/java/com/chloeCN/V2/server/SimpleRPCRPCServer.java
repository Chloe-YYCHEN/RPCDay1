package com.chloeCN.V2.server;

import com.chloeCN.V1.server.ServiceProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This implementation class represents the original BIO listening mode of java,
 * where a task comes in and a new thread is created to handle it
 * Refer WorkThread for processing tasks in
 * Exist service interface name -> Map of service objects
 */
public class SimpleRPCRPCServer implements RPCServer{

    private ServiceProvider serviceProvider;

    public SimpleRPCRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Start service");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to start service");
        }
    }

    @Override
    public void stop() {

    }
}
