package com.homework.secondweek.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static CloseableHttpClient httpClient
            = HttpClients.createDefault();

    public static final int OK = 200;

    public static int DEFAULT_CONNECT_TIMEOUT = 5000;
    public static int DEFAULT_SOCKET_TIMEOUT = 5000;

    public static RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom()
            .setSocketTimeout(DEFAULT_CONNECT_TIMEOUT)
            .setConnectTimeout(DEFAULT_SOCKET_TIMEOUT)
            .build();

    private static String DEFAULT_ENCODING = "UTF-8";

    private static Map<String, String> DEFAULT_HEADERS = new HashMap<String, String>();

    static {
        DEFAULT_HEADERS.put("Content-Type",ContentType.APPLICATION_JSON.toString());
    }

    public static <T> T getToObject(String url, Class<T> resultClass) throws IOException, RuntimeException {
        return getToObject(url, resultClass, null, DEFAULT_ENCODING);
    }

    public static <T> T getToObject(String url, Map<String, String> headers, Class<T> resultClass) throws IOException, RuntimeException {
        return getToObject(url, resultClass, headers, DEFAULT_ENCODING);
    }

    public static <T> T getToObject(String url,
                                    Class<T> resultClass, Map<String, String> headers, String encoding) throws IOException, RuntimeException {

        HttpGet httpGet = new HttpGet(url);
        return doExecute(httpGet, resultClass, headers, encoding);

    }

    public static <T> T postToObject(String url, Object param, Class<T> resultClass) throws IOException, RuntimeException {
        return postToObject(url, param, resultClass, null, DEFAULT_ENCODING);
    }

    public static <T> T postToObject(String url, Object param, Class<T> resultClass,  Map<String, String> headers) throws IOException, RuntimeException {
        return postToObject(url, param, resultClass, headers, DEFAULT_ENCODING);
    }


    public static <T> T postToObject(String url, Object param, Class<T> resultClass, Map<String, String> headers, String encoding) throws IOException, RuntimeException {
        HttpPost httpPost = new HttpPost(url);
        return doExecute(httpPost, resultClass, headers, encoding);
    }

    private static <T> T doExecute(HttpRequestBase request, Class<T> resultClass,
                                   Map<String, String> headers, String encoding) throws IOException {

        request.setConfig(DEFAULT_REQUEST_CONFIG);

        if (headers != null && headers.size() != 0) {
            for (Map.Entry<String, String> headersEntry : headers.entrySet()) {
                request.setHeader(headersEntry.getKey(), headersEntry.getValue());
            }
        } else {
            for (Map.Entry<String, String> headersEntry : DEFAULT_HEADERS.entrySet()) {
                request.setHeader(headersEntry.getKey(), headersEntry.getValue());
            }
        }

        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(request);
        } catch (IOException e) {
            if (httpResponse != null) {
                ((CloseableHttpResponse) httpResponse).close();
            }
            throw new RuntimeException("访问异常:" + e);
        }

        if (httpResponse.getStatusLine().getStatusCode() == OK) {
            //此时转译优化
            String result = EntityUtils.toString(httpResponse.getEntity(), encoding);
            Header[] rHeaders = httpResponse.getHeaders("Content-Type");
            ((CloseableHttpResponse) httpResponse).close();
            if (rHeaders != null) {
                String contentType = rHeaders[0].getValue();
                if (contentType.startsWith(ContentType.TEXT_PLAIN.getMimeType()) && resultClass.equals(String.class)) {
                   return (T)result;
                } else if (contentType.startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
                    return JSONObject.parseObject(result, resultClass);
                }
                throw new RuntimeException("不支持响应内容类型:" + rHeaders[0].getValue());
            }

        }
        throw new RuntimeException("访问异常:" + httpResponse.getStatusLine().getStatusCode());
    }

    private static void close() throws IOException {
        httpClient.close();
    }
}
