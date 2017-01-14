package com.example.zootopia.util;


import com.example.zootopia.application.AgoraConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Yilei Chu (ychu1) on 5/1/16.
 * Singleton design pattern
 */
public class HttpRequestUtil {
    private static final HttpRequestUtil instance = new HttpRequestUtil();

    private static final String host = AgoraConfiguration.ip;

    private static final int port = AgoraConfiguration.port;

    private static final String appPath = AgoraConfiguration.backend;

    private static String baseUrl;

    private static OkHttpClient client;

    /**
     * Private Constructor
     */
    private HttpRequestUtil() {
        client = new OkHttpClient();
        baseUrl = "http://" + host + ":" + port + "/" + appPath + "/";
    }

    public static HttpRequestUtil getHttpClient() {
        return instance;
    }

    /**
     * Send actual request and parse result to JSON object
     * @param request
     * @return
     */
    private JSONObject process(Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            if (res != null) {
                try {
                    JSONObject obj = new JSONObject(res);
                    return obj;
                } catch (JSONException e) {
                    System.err.println("Get result:" + res);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println(request.toString());
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Do get operation
     * @param uri
     * @param params
     * @return
     */
    public JSONObject doGet(String uri, Map<String, String> params) {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("http")
                .host(host)
                .port(port)
                .addPathSegment(appPath)
                .addPathSegment(uri);
        for (Map.Entry<String, String> entry : params.entrySet())
            builder.addQueryParameter(entry.getKey(), entry.getValue());

        HttpUrl url = builder.build();
        System.out.println("url:" + url.toString());
        Request request = new Request.Builder()
                .url(url)
                .build();
        return process(request);
    }

    /**
     * Do post operation
     * @param uri
     * @param params
     * @return
     */
    public JSONObject doPost(String uri, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet())
            builder.add(entry.getKey(), entry.getValue());

        RequestBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(baseUrl + uri)
                .post(formBody)
                .build();

        return process(request);
    }

    /**
     * Do delete operation
     * @param uri
     * @param params
     * @return
     */
    public JSONObject doDelete(String uri, Map<String, String> params) {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("http")
                .host(host)
                .port(port)
                .addPathSegment(appPath)
                .addPathSegment(uri);
        for (Map.Entry<String, String> entry : params.entrySet())
            builder.addQueryParameter(entry.getKey(), entry.getValue());

        HttpUrl url = builder.build();
        System.out.println("url:" + url.toString());
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        return process(request);
    }
}