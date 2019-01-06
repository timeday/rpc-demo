package provider;

import framework.ProtocolFactory;
import framework.Url;
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

        //注册服务
        Url url=new Url("localhost",8080);
        Register.register(url, HelloService.class.getName(),HelloServiceImpl.class);
        //启动服务
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);

    }
}
