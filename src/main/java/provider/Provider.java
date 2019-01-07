package provider;

import rpcFramework.PropertiesUtils;
import rpcFramework.ProtocolFactory;
import rpcFramework.Url;
import protocol.Protocol;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.Register;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 01:01
 **/
public class Provider {

    public static void main(String[] args) {

        String host = PropertiesUtils.getSystemInfo("host");
        String port = PropertiesUtils.getSystemInfo("port");
        //注册服务
        Url url=new Url(host,Integer.valueOf(port));
            Register.register(url, HelloService.class.getName(),HelloServiceImpl.class);
            //启动服务
            Protocol protocol = ProtocolFactory.getProtocol();
            protocol.start(url);


    }
}
