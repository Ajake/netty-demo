package com.netty.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketServer {
    private static int port = 8080;

    public static void main(String[] args) throws Exception {
        EventLoopGroup bootstrapGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bootstrapGroup, workGroup);
            serverBootstrap
                    .channel(NioServerSocketChannel.class)
                    .childHandler(null);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bootstrapGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
