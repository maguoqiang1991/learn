package io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf {
    public static void main(String[] args) {
        // ����byteBuf���󣬸ö����ڲ�����һ���ֽ�����byte[10]
        // ͨ��readerindex��writerIndex��capacity����buffer�ֳ���������
        // �Ѿ���ȡ������[0,readerindex)
        // �ɶ�ȡ������[readerindex,writerIndex)
        // ��д������: [writerIndex,capacity)
        ByteBuf byteBuf = Unpooled.buffer(10);
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 8; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.getByte(i));
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.readByte());
        }
        System.out.println("byteBuf=" + byteBuf);


        //��Unpooled�����ഴ��ByteBuf
        ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello,zhuge!", CharsetUtil.UTF_8);
        //ʹ����صķ���
        if (byteBuf2.hasArray()) {
            byte[] content = byteBuf2.array();
            //�� content ת���ַ���
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf=" + byteBuf2);

            System.out.println(byteBuf2.readerIndex()); // 0
            System.out.println(byteBuf2.writerIndex()); // 12
            System.out.println(byteBuf2.capacity()); // 36

            System.out.println(byteBuf2.getByte(0)); // ��ȡ����0���λ�õ��ַ�h��ascii�룬h=104

            int len = byteBuf2.readableBytes(); //�ɶ����ֽ���  12
            System.out.println("len=" + len);

            //ʹ��forȡ�������ֽ�
            for (int i = 0; i < len; i++) {
                System.out.println((char) byteBuf2.getByte(i));
            }

            //��Χ��ȡ
            System.out.println(byteBuf2.getCharSequence(0, 6, CharsetUtil.UTF_8));
            System.out.println(byteBuf2.getCharSequence(6, 6, CharsetUtil.UTF_8));
        }
    }
}
