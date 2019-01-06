package protocol.dubbo;

import framework.Invocation;
import framework.Url;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import register.Register;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * 服务端操作，由服务端我们看到具体的数据传输操作是进行序列化的，具体的操作还是比较简单的，
 * 就是获取发送过来的信息，这样就可以通过反射获得类名，根据函数名和参数值，执行具体的操作，将执行结果发送给客户端
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:03
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("BaseClient1Handler channelActive");
//        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("BaseClient1Handler channelInactive");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Invocation invocation=(Invocation)msg;

        InetSocketAddress socketAddress =(InetSocketAddress)ctx.channel().localAddress();
        //有问题
        Class aClass = Register.get(invocation.getInterfaceName(), new Url("localhost", socketAddress.getPort()));

        Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object invoke = method.invoke(aClass.newInstance(), invocation.getParams());
        System.out.println("Netty-----------"+invoke.toString());
        ctx.writeAndFlush("Netty:"+invoke);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
