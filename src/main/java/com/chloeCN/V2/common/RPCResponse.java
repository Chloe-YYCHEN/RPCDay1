package com.chloeCN.V2.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 引入状态码和状态信息表示服务调用成功还是失败
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    private int code;

    private String message;

    private Object data;

    public static RPCResponse success(Object data){
        return RPCResponse.builder().code(200).data(data).build();
    }

    public static RPCResponse fail(){
        return RPCResponse.builder().code(500).message("service error").build();
    }
}
