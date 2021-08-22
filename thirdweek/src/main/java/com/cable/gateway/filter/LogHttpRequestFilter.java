package com.cable.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.log4j.Logger;

public class LogHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        Logger.getLogger(LogHttpResponseFilter.class).info("响应开始");
        fullRequest.headers().set("startTime", System.currentTimeMillis());

    }
}
