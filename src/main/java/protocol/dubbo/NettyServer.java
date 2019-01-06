package protocol.dubbo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;


/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 03:03
 **/
public class NettyServer {


    public void start(String hostname,int port){
        NioEventLoopGroup nioEventLoopGroup=new NioEventLoopGroup();
        try {
            final ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(nioEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline=socketChannel.pipeline();
                            pipeline.addLast("decoder",new ObjectDecoder(ClassResolvers
                                    .weakCachingConcurrentResolver(this.getClass().getClassLoader())
                            ));
                            pipeline.addLast("encoder",new ObjectEncoder());
                            pipeline.addLast("handler",new NettyServerHandler());
                        }
                    });
                    InetSocketAddress inetSocketAddress=new InetSocketAddress(port);
                    ChannelFuture future = serverBootstrap.bind(hostname,port).sync();
                    System.out.println("Server start listen at " + port );
                    future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            nioEventLoopGroup.shutdownGracefully();
        }
    }

}
