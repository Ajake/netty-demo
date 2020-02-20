package com.netty.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author RichScout
 * @version v1.0.0
 * @see com.netty.demo.server
 * @since 2020-02-16 21:07:59
 */
public class DemoNettyServer {
    private static int port = 8080;

    public static void main(String[] args) throws Exception {
        //定义线程组
        //主线程组，接受客户端的连接，但是不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //从线程组，处理逻辑
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //Netty 服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitalizer());
            //启动server，设置端口号，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //监听关闭channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭线程组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
