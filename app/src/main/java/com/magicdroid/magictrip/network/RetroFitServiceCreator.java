package com.magicdroid.magictrip.network;

import android.content.Context;


import com.magicdroid.magictrip.utililty.AppConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitServiceCreator {

    private static Retrofit retrofit = null;

    public static <T> T getClient(final Class<T> clazz, final String endPoint, Context context) {
        try {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // builder.sslSocketFactory(sslSocketFactory, mod509);
            builder.addNetworkInterceptor(httpLoggingInterceptor);

            final Cache responseCache = new Cache(context.getCacheDir(), 10 * 1024 * 1024);
            builder.cache(responseCache);

            builder.addNetworkInterceptor(provideCacheInterceptor());
            builder.addInterceptor(provideOfflineCacheInterceptor(context));

            builder.readTimeout(AppConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
            builder.connectTimeout(AppConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS);

            //builder.followRedirects(true);
            //builder.retryOnConnectionFailure(true);

            OkHttpClient client = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrofit.create(clazz);
    }

    private static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (request.url().toString().contains("cardPriority.html") || request.url().toString().contains("getbrokerdtl.html")) {

                    //Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
                    // re-write response header to force use of cache
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxAge(1, TimeUnit.MINUTES)
                            .build();

                    return response.newBuilder()
                            .header("Cache-Control", cacheControl.toString())
                            .header("User-Agent", "Retrofit-Sample-App")
                            .build();
                } else {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxAge(0, TimeUnit.MINUTES)
                            .build();

                    return response.newBuilder()
                            .header("Cache-Control", cacheControl.toString())
                            .build();
                }
            }
        };
    }

    private static Interceptor provideOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!new NetworkManager(context).isInternetConnected()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }
}