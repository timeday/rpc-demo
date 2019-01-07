package protocol.dubbo;

import rpcFramework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:28
 **/
public class NettyClient {

    public static  String send(String hostname, Integer port, Invocation invocation) {

        NettyClientHandler resultHandler = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        // 向pipeline中添加编码、解码、业务处理的handler
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            pipeline.addLast("decoder",new ObjectDecoder(ClassResolvers
                                    .weakCachingConcurrentResolver(this.getClass().getClassLoader())
                            ));
                            pipeline.addLast("encoder",new ObjectEncoder());
                            pipeline.addLast("handler",resultHandler);
                        }
                    });
             // 链接服务器
             ChannelFuture future = b.connect(hostname, port).sync();
            //将invocation对象写入outbundle处理后发出
            future.channel().writeAndFlush(invocation).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

        return resultHandler.getResponse().toString();
    }
}
