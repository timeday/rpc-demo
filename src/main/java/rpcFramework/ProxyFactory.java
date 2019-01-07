package rpcFramework;

import protocol.Protocol;
import register.Register;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 02:06
 **/
public class ProxyFactory {


    public static <T> T getProxy(final Class interfaceClass){

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(),args,method.getParameterTypes());
                Protocol protocol = ProtocolFactory.getProtocol();
                Url url= Register.getRandomUrl(interfaceClass.getName());
                return protocol.send(url,invocation);
            }
        });

    }


}
