package protocol.http;

import rpcFramework.Invocation;
import rpcFramework.Url;
import protocol.Protocol;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:27
 **/
public class ProtocolHttpImpl implements Protocol {

    @Override
    public void start(Url url) {

        HttpServer httpServer=new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());


    }

    @Override
    public String send(Url url, Invocation invocation) {

        HttpClient httpClient=new HttpClient();
        return httpClient.post(url.getHostname(),url.getPort(),invocation);

    }
}
