package io.netty.mychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.mychat.handler.ChatServerHandler;

/**
 * @author maguoqiang
 */
public class ChatServer {

    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) throws InterruptedException {

        //开启线程组
        //accept group
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //read write group
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            //开启服务器
            ServerBootstrap bootstrap = new ServerBootstrap();

            //服务器设置组，进行事件监听，和回调
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //处理read or write 事件
                            //向pipeline加入解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //向pipeline加入编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new ChatServerHandler());
                        }
                    });


            ChannelFuture cf = bootstrap.bind(SERVER_PORT).sync();
            System.out.println("my chat netty server start。。");
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


}
