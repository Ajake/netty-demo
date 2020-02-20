package com.net.bio.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class BioServer {
    protected static final Logger log = LoggerFactory.getLogger(BioServer.class);
    //定义端口号
    public static final int port = 8080;
    //定义数组大小
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try {
            //创建serverSocket
            ServerSocket serverSocket = new ServerSocket();
            //绑定端口号
            serverSocket.bind(new InetSocketAddress(port));
            while (true) {
                log.info(">>>开始等待客户端连接<<<");
                Socket socket = serverSocket.accept();
                //读取数据
                int read = socket.getInputStream().read(bytes);
                String result = new String(bytes);
                log.info(">>>服务端获取的数据<<<");
                log.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
