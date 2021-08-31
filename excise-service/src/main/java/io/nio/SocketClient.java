package io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author maguoqiang
 * @date 2021-06-03 14:26
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        //向服务端发送数据
        char i = (char) System.in.read();


        InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象
        BufferedReader br = new BufferedReader(is); //拿构造的方法传到BufferedReader中，此时获取到的就是整个缓存流
        //该方法中有个IOExcepiton需要捕获
        String name = br.readLine();
        System.out.println("ReadTest Output:" + name);


        socket.getOutputStream().write(name.getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes));
        socket.close();
    }
}
