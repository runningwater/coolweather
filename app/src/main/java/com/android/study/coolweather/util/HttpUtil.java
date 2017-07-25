package com.android.study.coolweather.util;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by runningwater on 2017/7/21.
 */

public class HttpUtil {
    private static String TAG = "HttpUtil";

    /**
     * 发送 Http 请求
     *
     * @param address  url
     * @param callback 回调
     */
    public static void sendOkHttpRequest(String address, Callback callback) {
        Log.d(TAG, "sendOkHttpRequest: address => " + address);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
