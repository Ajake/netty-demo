package com.netty.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import java.util.logging.Logger;

/**
 * <p>
 * Description:自定义类
 * </p>
 *
 * @author RichScout
 * @version v1.0.0
 * @see com.netty.demo.server
 * @since 2020-02-16 21:37:48
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static InternalLogger log = Slf4JLoggerFactory.getInstance(CustomHandler.class);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        //获取channel
        Channel channel = channelHandlerContext.channel();

        if (httpObject instanceof HttpRequest) {
            //显示客户端远程地址
            System.out.println(channel.remoteAddress());

            //定义发送数据信息
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Netty", CharsetUtil.UTF_8);
            //构建响应类
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    byteBuf);
            //添加头部
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            //返回到客户端
            channelHandlerContext.writeAndFlush(response);

        }

    }

    /**
     * channel注册
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel注册");
        super.channelRegistered(ctx);
    }

    /**
     * channel移除
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel移除");
        super.channelUnregistered(ctx);
    }

    /**
     * channel活跃
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel活跃");
        super.channelActive(ctx);
    }

    /**
     * channel不活跃
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel不活跃");
        super.channelInactive(ctx);
    }

    /**
     * channel读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel读取完毕");
        super.channelReadComplete(ctx);
    }

    /**
     * channel用户事件触发
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("Channel用户事件触发");
        super.userEventTriggered(ctx, evt);
    }

    /**
     * channel可更改
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel可更改状态");
        super.channelWritabilityChanged(ctx);
    }

    /**
     * channel捕获异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("channel捕获异常");
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 自定义类添加
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("自定义类添加");
        super.handlerAdded(ctx);
    }

    /**
     * 自定义类移除
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("自定义类移除");
        super.handlerRemoved(ctx);
    }
}
