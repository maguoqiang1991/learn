package io.nio.old;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

    //public static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        // ����һ���ڱ��ض˿ڽ��м����ķ���Socketͨ��.������Ϊ��������ʽ
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //��������Ϊ������������selector��ע�ᣬ����ᱨ��selectorģʽ������Ƿ�����ģʽ
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(9000));
        // ����һ��ѡ����selector
        Selector selector = Selector.open();
        // ��ServerSocketChannelע�ᵽselector�ϣ�����selector�Կͻ���accept���Ӳ�������Ȥ
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("�ȴ��¼���������");
            // ��ѯ����channel���key��select�������ģ�accept()Ҳ��������
            int select = selector.select();

            System.out.println("���¼������ˡ���");
            // �пͻ������󣬱���ѯ������
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                //ɾ�������Ѵ����key����ֹ�´�select�ظ�����
                it.remove();
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("�пͻ��������¼������ˡ���");
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            //NIO���������֣��˴�accept�����������ģ�����������Ϊ�Ƿ����������¼��������������������ִ���꣬��������
            //�������������󲻻�����ȴ��ͻ��˵����ݷ���
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            //ͨ��Selector����Channelʱ�Զ��¼�����Ȥ
            sc.register(key.selector(), SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            System.out.println("�пͻ������ݿɶ��¼������ˡ���");
            SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //NIO����������:����read����������������������¼���Ӧģ�ͣ������õ�read����ʱ�϶��Ƿ����˿ͻ��˷������ݵ��¼�
            int len = sc.read(buffer);
            if (len != -1) {
                System.out.println("��ȡ���ͻ��˷��͵����ݣ�" + new String(buffer.array(), 0, len));
            }
            ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
            sc.write(bufferToWrite);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            System.out.println("write�¼�");
            // NIO�¼�������ˮƽ����
            // ʹ��Java��NIO��̵�ʱ����û�����ݿ�������д��ʱ��Ҫȡ��д�¼���
            // ������������д��ʱ����ע��д�¼�
            key.interestOps(SelectionKey.OP_READ);
            //sc.close();
        }
    }
}