package rpcFramework;

import protocol.Protocol;
import protocol.dubbo.ProtocolDubboImpl;
import protocol.http.ProtocolHttpImpl;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:32
 **/
public class ProtocolFactory {

    /**
     * 多个协议
     * @return
     */
    public static Protocol getProtocol(){
        String protocolName =  PropertiesUtils.getSystemInfo("protocolName");

        if (protocolName==null || protocolName.equalsIgnoreCase(""))
           protocolName="http";
        switch (protocolName){

            case "http":
                return new ProtocolHttpImpl();
            case "dubbo":
                return new ProtocolDubboImpl();
            default:
                break;

        }
        return new ProtocolHttpImpl();
    }


}
