package com.net.nio.server;

import com.net.bio.server.BioServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NioServer {
    protected static final Logger log = LoggerFactory.getLogger(NioServer.class);
    //定义缓冲区
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024*10);
    //定义连接集合
    public static List<SocketChannel> selector = new ArrayList<>();
    public static void main(String[] args) {
        try {
            //1、创建serverSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //2、绑定端口号
            serverSocketChannel.bind(new InetSocketAddress(8080));
            //3、非阻塞式
            serverSocketChannel.configureBlocking(false);
            //4、不断循环等待连接进入
            while (true) {
                SocketChannel channel = serverSocketChannel.accept();
                //非阻塞式:不管是否获取到数据，都会立马返回一个结果，这里的channel没有获取到就是一个null，不断循环重试
                if (channel != null) {
                    channel.configureBlocking(false);
                    log.info("远程IP连接{}", channel.getRemoteAddress().toString());
                    selector.add(channel);
                }
                //主动轮询检查连接是否有数据，一个线程维护多个不同的IO操作
                for (SocketChannel scl : selector) {
                    int read = scl.read(byteBuffer);
                    if (read > 0) {
                        byteBuffer.flip();
                        Charset charset = Charset.forName("UTF-8");
                        String receive = charset.newDecoder().decode(byteBuffer.asReadOnlyBuffer()).toString();
                        log.info("线程名称:{}" + "收到信息:{}", Thread.currentThread().getName(), receive);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
