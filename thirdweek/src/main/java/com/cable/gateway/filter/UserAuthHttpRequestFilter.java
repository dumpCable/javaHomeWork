package com.cable.gateway.filter;

import com.cable.gateway.config.UserConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class UserAuthHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String userId = fullRequest.headers().get("userId");
        if (UserConfig.blockList.contains(userId)) {
            throw new IllegalStateException("非法用户");
        }
    }
}
