package com.cable.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.log4j.Logger;

public class LogHttpResponseFilter implements HttpResponseFilter {

    @Override
    public void filter(FullHttpResponse response) {
        Logger.getLogger(LogHttpResponseFilter.class).info("响应完成");

    }


}
