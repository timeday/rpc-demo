package provider.impl;

import provider.api.HelloService;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:26
 **/
public class HelloServiceImpl implements HelloService {
    public String sayHello(String username) {
        return "hello-------"+username;
    }
}
