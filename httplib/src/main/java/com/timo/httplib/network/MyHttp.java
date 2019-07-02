package com.timo.httplib.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.timo.httplib.R;
import com.timo.httplib.network.base.CaheInterceptor;
import com.timo.httplib.network.base.HttpConstancts;
import com.timo.httplib.network.base.NovateCookieManger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyHttp {
    private static volatile Retrofit retrofit_string;
    private static volatile Retrofit retrofit_gson;
    private static OkHttpClient httpClient;
    private static String baseUrl;
    private static MyHttpListener mListener;

    private MyHttp() {
    }

    public static void init(String url, OkHttpClient client, MyHttpListener listener) {
        baseUrl = url;
        httpClient = client;
        mListener = listener;
    }

    public static void showLoadingDialog(Context context) {
        if (mListener != null) {
            mListener.showLoadingDialog(context);
        }
    }

    public static void dissmissLoadingDialog() {
        if (mListener != null) {
            mListener.dissmissLoadingDialog();
        }
    }

    public static void showToast(String message) {
        if (mListener != null) {
            mListener.showToast(message);
        }
    }

    public static void logE(Throwable e) {
        if (mListener != null) {
            mListener.logE(e);
        }
    }

    public static void init(String url, MyHttpListener listener) {
        init(url, null, listener);
    }


    /**
     * 获取Gson类型数据
     *
     * @param context
     * @return
     */
    @SuppressLint("WrongConstant")
    public static <T> T getGsonApi(final Context context, Class<T> service) {
        if (TextUtils.isEmpty(baseUrl)) {
            Toast.makeText(context, context.getString(R.string.error_http_not_init), Toast.LENGTH_SHORT).show();
            return null;
        }
        if (retrofit_gson == null) {
            synchronized (MyHttp.class) {
                if (retrofit_gson == null) {
                    if (httpClient == null) {
                        httpClient = getUnsafeOkHttpClient();
                    }
                    retrofit_gson = new Retrofit.Builder().baseUrl(baseUrl)
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(new OkHttpClient.Builder()
                                    .cookieJar(new NovateCookieManger(context))
                                    .cache(new Cache(new File(context.getExternalCacheDir(), HttpConstancts.http_cache), 10 * 1024 * 1024))
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Interceptor.Chain chain) throws IOException {
                                            if (isNetworkAvailable(context)) {
                                                // read from cache for 0 s  有网络不会使用缓存数据
                                                int maxAge = 10;
                                                Request request = chain.request().newBuilder()
//                                                        .removeHeader("Pragma")
//                                                        .removeHeader("Cache-Control")
//                                                        .header("Cache-Control", "public, max-age=" + maxAge)
//                                                        .addHeader("Authorization", token)
//                                                        .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                                                        .build();
                                                return chain.proceed(request);
                                            } else {
                                                //无网络时强制使用缓存数据
                                                int maxStale = 60 * 60 * 24 * 3;
                                                Request request = chain.request().newBuilder()
                                                        .cacheControl(CacheControl.FORCE_CACHE)
//                                                        .removeHeader("Pragma")
//                                                        .removeHeader("Cache-Control")
//                                                        .addHeader("Authorization", token)
//                                                        .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                                                        .build();
                                                return chain.proceed(request);
                                            }
                                        }

                                    })
                                    .addNetworkInterceptor(new CaheInterceptor(context))
                                    .connectTimeout(30, TimeUnit.SECONDS)
                                    .build())
                            .build();
                }
            }
        }
        return retrofit_gson.create(service);
    }

    /**
     * check NetworkAvailable
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (null == manager)
            return false;
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (null == info || !info.isAvailable())
            return false;
        return true;
    }

    /**
     * 获取String类型数据
     *
     * @param context
     * @return
     */
    @SuppressLint("WrongConstant")
    public static <T> T getStringApi(final Context context, Class<T> service) {
        if (TextUtils.isEmpty(baseUrl)) {
            Toast.makeText(context, context.getString(R.string.error_http_not_init), Toast.LENGTH_SHORT).show();
            return null;
        }
        if (retrofit_string == null) {
            synchronized (MyHttp.class) {
                if (retrofit_string == null) {
                    if (httpClient == null) {
                        httpClient = getUnsafeOkHttpClient();
                    }
                    retrofit_string = new Retrofit.Builder().baseUrl(baseUrl)
                            .client(httpClient)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(new OkHttpClient.Builder()
                                    .cookieJar(new NovateCookieManger(context))
                                    .cache(new Cache(new File(context.getExternalCacheDir(), HttpConstancts.http_cache), 10 * 1024 * 1024))
                                    .addInterceptor(new Interceptor() {
                                        @Override
                                        public Response intercept(Interceptor.Chain chain) throws IOException {
                                            if (isNetworkAvailable(context)) {
                                                // read from cache for 0 s  有网络不会使用缓存数据
                                                int maxAge = 10;
                                                Request request = chain.request().newBuilder()
//                                                        .removeHeader("Pragma")
//                                                        .removeHeader("Cache-Control")
//                                                        .header("Cache-Control", "public, max-age=" + maxAge)
                                                        .addHeader("phone_brand", android.os.Build.BRAND == null ? "" : android.os.Build.BRAND)
                                                        .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                                                        .build();
                                                return chain.proceed(request);
                                            } else {
                                                //无网络时强制使用缓存数据
                                                int maxStale = 60 * 60 * 24 * 3;
                                                Request request = chain.request().newBuilder()
                                                        .cacheControl(CacheControl.FORCE_CACHE)
//                                                        .removeHeader("Pragma")
//                                                        .removeHeader("Cache-Control")
//                                                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                                        .addHeader("phone_brand", android.os.Build.BRAND == null ? "" : android.os.Build.BRAND)
                                                        .addHeader("phone_type", android.os.Build.MODEL == null ? "" : android.os.Build.MODEL)
                                                        .build();
                                                return chain.proceed(request);
                                            }
                                        }

                                    })
                                    .addNetworkInterceptor(new CaheInterceptor(context))
                                    .connectTimeout(30, TimeUnit.SECONDS)
                                    .build())
                            .build();
                }
            }
        }
        return retrofit_string.create(service);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
