package io.nio.old;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
    //ͨ��������
    private Selector selector;

    /**
     * �����ͻ��˲���
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.initClient("127.0.0.1", 9000);
        client.connect();
    }

    /**
     * ���һ��Socketͨ�������Ը�ͨ����һЩ��ʼ���Ĺ���
     *
     * @param ip   ���ӵķ�������ip
     * @param port ���ӵķ������Ķ˿ں�
     * @throws IOException
     */
    public void initClient(String ip, int port) throws IOException {
        // ���һ��Socketͨ��
        SocketChannel channel = SocketChannel.open();
        // ����ͨ��Ϊ������
        channel.configureBlocking(false);
        // ���һ��ͨ��������
        this.selector = Selector.open();

        // �ͻ������ӷ�����,��ʵ����ִ�в�û��ʵ�����ӣ���Ҫ��listen���������е�
        //��channel.finishConnect() �����������
        channel.connect(new InetSocketAddress(ip, port));
        //��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_CONNECT�¼���
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
     *
     * @throws IOException
     */
    public void connect() throws IOException {
        // ��ѯ����selector
        while (true) {
            selector.select();
            // ���selector��ѡ�е���ĵ�����
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                // ɾ����ѡ��key,�Է��ظ�����
                it.remove();
                // �����¼�����
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // ����������ӣ����������
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    // ���óɷ�����
                    channel.configureBlocking(false);
                    //��������Ը�����˷�����ϢŶ
                    ByteBuffer buffer = ByteBuffer.wrap("HelloServer".getBytes());
                    channel.write(buffer);
                    //�ںͷ�������ӳɹ�֮��Ϊ�˿��Խ��յ�����˵���Ϣ����Ҫ��ͨ�����ö���Ȩ�ޡ�
                    channel.register(this.selector, SelectionKey.OP_READ);                                            // ����˿ɶ����¼�
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    /**
     * �����ȡ����˷�������Ϣ ���¼�
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        //�ͷ���˵�read����һ��
        // �������ɶ�ȡ��Ϣ:�õ��¼�������Socketͨ��
        SocketChannel channel = (SocketChannel) key.channel();
        // ������ȡ�Ļ�����
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = channel.read(buffer);
        if (len != -1) {
            System.out.println("�ͻ����յ���Ϣ��" + new String(buffer.array(), 0, len));
        }
    }
}