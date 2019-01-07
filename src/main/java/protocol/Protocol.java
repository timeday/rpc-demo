package protocol;

import rpcFramework.Invocation;
import rpcFramework.Url;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:26
 **/
public interface Protocol {


    void start(Url url);

    String send(Url url, Invocation invocation);


}
