package com.chloeCN.V2.server;

import com.chloeCN.V1.common.RPCRequest;
import com.chloeCN.V1.common.RPCResponse;
import com.chloeCN.V1.server.ServiceProvider;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * it is server-side, we know that the format of the received request is RPCRequest
 * Object type is fine, just force the transformation
 */
@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {

    private ServiceProvider serviceProvider;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {
        RPCResponse response = getResponse(rpcRequest);
        channelHandlerContext.writeAndFlush(response);
        channelHandlerContext.close();
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable throwable) throws Exception{
        throwable.printStackTrace();
        channelHandlerContext.close();
    }

    RPCResponse getResponse(RPCRequest request) {
        //get service name
        String interfaceName = request.getInterfaceName();
        //get the corresponding service implementation class on the server side
        //corresponding 相应的
        Object service = serviceProvider.getService(interfaceName);
        //Reflection call method
        Method method = null;
        try {
            method = service.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
            Object invoke = method.invoke(service,request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("Method execution error");
            return RPCResponse.fail();
        }
    }
}
