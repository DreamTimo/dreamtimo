package com.timo.httplib.network.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * caheInterceptor
 * Created by yi on 2016-12-03.
 */
public class CaheInterceptor implements Interceptor {

    private Context context;

    public CaheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (isNetworkAvailable(context)) {
            Response response = chain.proceed(request);
            // read from cache for 0 s  有网络不会使用缓存数据
            int maxAge = 10;
            return response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .addHeader("phone_brand", android.os.Build.BRAND == null ? "" : android.os.Build.BRAND)
                    .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                    .build();
        } else {
            //无网络时强制使用缓存数据
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
            int maxStale = 60 * 60 * 24 * 3;
            return response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .addHeader("phone_brand", android.os.Build.BRAND == null ? "" : android.os.Build.BRAND)
                    .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                    .build();
        }
    }

    /**
     * check NetworkAvailable
     *
     * @param context
     * @return
     */
    public boolean isNetworkAvailable(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (null == manager)
            return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (null == info || !info.isAvailable())
            return false;
        return true;
    }
}
