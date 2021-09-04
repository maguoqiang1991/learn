package io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        //�ͻ�����Ҫһ���¼�ѭ����
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //�����ͻ�����������
            //ע��ͻ���ʹ�õĲ���ServerBootstrap����Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //������ز���
            bootstrap.group(group) //�����߳���
                    .channel(NioSocketChannel.class) // ʹ��NioSocketChannel��Ϊ�ͻ��˵�ͨ��ʵ��
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //���봦����
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            System.out.println("netty client start����");
            //�����ͻ���ȥ���ӷ�������
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 9000).sync();
            //��ͨ���رս��м���
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}