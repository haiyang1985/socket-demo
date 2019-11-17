package org.ghy.socket;

import javax.net.SocketFactory;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientSocket {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        DateFormat df = new SimpleDateFormat("hh:mm:ss SSS");
        int port = 36666;

        // 与服务端建立连接
        SocketFactory socketFactory = SocketFactory.getDefault();
        Socket socket = socketFactory.createSocket();
        SocketAddress localaddr = new InetSocketAddress(9992);
        SocketAddress remoteaddr = new InetSocketAddress(host, port);

        System.out.println(df.format(new Date()) + "开始调用socket.bind(");
        socket.bind(localaddr);
        Thread.sleep(1000);
        System.out.println(df.format(new Date()) + "开始调用socket.connect(");
        socket.connect(remoteaddr, 10000000);

        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        Thread.sleep(1000);

        String name = df.format(new Date()) + "hello, i am good";
        System.out.println(df.format(new Date()) + "开始调用socket.getOutputStream().write");
        socket.getOutputStream().write(name.getBytes("UTF-8"));

        Thread.sleep(1000);
        String company = df.format(new Date()) + "i am from China.";
        System.out.println(df.format(new Date()) + "开始调用socket.getOutputStream().write");
        socket.getOutputStream().write(company.getBytes("UTF-8"));
        Thread.sleep(1000);
        System.out.println(df.format(new Date()) + "开始调用outputStream.close()");
        socket.close();
        Thread.sleep(1000);
        System.out.println(df.format(new Date()) + "开始调用socket.close()");
        outputStream.close();
    }
}
