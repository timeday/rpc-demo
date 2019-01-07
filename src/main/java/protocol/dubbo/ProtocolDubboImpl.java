package protocol.dubbo;

import rpcFramework.Invocation;
import rpcFramework.Url;
import protocol.Protocol;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:27
 **/
public class ProtocolDubboImpl implements Protocol {

    @Override
    public void start(Url url) {

        NettyServer nettyServer=new NettyServer();

        nettyServer.start(url.getHostname(),url.getPort());


    }

    @Override
    public String send(Url url, Invocation invocation) {

        NettyClient nettyClient=new NettyClient();

       return nettyClient.send(url.getHostname(),url.getPort(),invocation);

    }
}
