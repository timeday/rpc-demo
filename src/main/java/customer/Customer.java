package customer;

import framework.ProxyFactory;
import provider.api.HelloService;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 01:35
 **/
public class Customer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String res = helloService.sayHello("rpc-123");
        System.out.println(res);
    }
}
