package com.netty.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>
 * Description:初始化，channel注册后，执行里面的相应的初始化方法
 * </p>
 *
 * @author RichScout
 * @version v1.0.0
 * @see com.netty.demo.server
 * @since 2020-02-16 21:26:20
 */
public class NettyServerInitalizer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //通过SocketChannel获取管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        //通过管道，添加handler
        //HttpServerCodec由netty自己提供的助手类，可以理解为拦截器
        //请求到服务端，解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //自定义handler
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
