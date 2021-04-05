package edu.lnu.audiotunes.data.network.services;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitInfo = null;

    private static final String BASE_URL = "https://rss.itunes.apple.com/api/v1/us/";
    private static final String BASE_INFO_URL = "http://itunes.apple.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) retrofit = createRetrofitInstance();
        return retrofit;
    }

    public static Retrofit getRetrofitInfoInstance() {
        if (retrofitInfo == null) retrofitInfo = createRetrofitInfoInstance();
        return retrofitInfo;
    }

    private static Retrofit createRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createLoggingInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static Retrofit createRetrofitInfoInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_INFO_URL)
                .client(createLoggingInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient createLoggingInterceptorClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
