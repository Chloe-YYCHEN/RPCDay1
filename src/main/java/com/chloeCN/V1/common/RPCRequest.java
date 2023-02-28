package com.chloeCN.V1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class RPCRequest implements Serializable {
    //    Service class name, client only get interface name, Use the interface name to point to the implementation class in the server side
    private String interfaceName;
    //    Method name
    private String methodName;
    //    Parameter List
    private Object[] params;
    //    Parameter Type
    private Class<?>[] paramsTypes;
}
