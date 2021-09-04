package io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws Exception {
        //���������߳���bossGroup��workerGroup, ���е����߳�NioEventLoop�ĸ���Ĭ��Ϊcpu����������
        // bossGroupֻ�Ǵ����������� ,�����ĺͿͻ���ҵ�����ύ��workerGroup���
        EventLoopGroup bossGroup = new NioEventLoopGroup(10);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //�����������˵���������
            ServerBootstrap bootstrap = new ServerBootstrap();
            //ʹ����ʽ��������ò���
            bootstrap.group(bossGroup, workerGroup) //���������߳���
                    .channel(NioServerSocketChannel.class) //ʹ��NioServerSocketChannel��Ϊ��������ͨ��ʵ��
                    // ��ʼ�����������Ӷ��д�С������˴���ͻ�������������˳�����,����ͬһʱ��ֻ�ܴ���һ���ͻ������ӡ�
                    // ����ͻ���ͬʱ����ʱ��,����˽����ܴ���Ŀͻ�������������ڶ����еȴ�����
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//����ͨ����ʼ���������ó�ʼ������

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //��workerGroup��SocketChannel���ô�����
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("netty server start����");
            //��һ���˿ڲ���ͬ��, ������һ��ChannelFuture�첽����ͨ��isDone()�ȷ��������ж��첽�¼���ִ�����
            //����������(���󶨶˿�)��bind���첽������sync�����ǵȴ��첽����ִ�����
            ChannelFuture cf = bootstrap.bind(9000).sync();
            //��cfע����������������ǹ��ĵ��¼�
            /*cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("�����˿�9000�ɹ�");
                    } else {
                        System.out.println("�����˿�9000ʧ��");
                    }
                }
            });*/
            //��ͨ���رս��м�����closeFuture���첽����������ͨ���ر�
            // ͨ��sync����ͬ���ȴ�ͨ���رմ�����ϣ�����������ȴ�ͨ���ر����
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}