package org.ghy.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat("hh:mm:ss SSS ");
        // 监听指定的端口
        int port = 36666;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println(df.format(new Date()) + "ServerSocket对象已创建。");
        Socket socket = serverSocket.accept();
        System.out.println(df.format(new Date()) + "accept到链接。");
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        System.out.println(df.format(new Date()) + "获取到InputStream.");
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        Thread.sleep(4000);
        System.out.println(df.format(new Date()) + "开始读取数据。");
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("由于此时client端已发送Fin报文，此时执行写操作，client端会恢复异常关闭rst");
        // socket.getOutputStream().write("can not write because client has closed".getBytes());
        System.out.println(df.format(new Date()) + "读取到的数据" + sb.toString());
        Thread.sleep(2000);
        System.out.println(df.format(new Date()) + "开始调用inputStream.close");
        inputStream.close();
        Thread.sleep(2000);
        System.out.println(df.format(new Date()) + "开始调用socket.close");
        socket.close();
        Thread.sleep(2000);
        System.out.println(df.format(new Date()) + "开始调用server.close");
        serverSocket.close();
        System.in.read();
    }
}
