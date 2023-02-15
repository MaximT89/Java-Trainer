package com.second.world.javatest.core_di;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.second.world.javatest.BuildConfig;
import com.second.world.javatest.core.UserAgentInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BaseApiModule {

    @Singleton
    @Provides
    HttpLoggingInterceptor provideLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level interceptorLevel = HttpLoggingInterceptor.Level.BODY;
        interceptor.level(interceptorLevel);
        return interceptor;
    }

    @Provides
    @Singleton
    UserAgentInterceptor provideUserAgentInterceptor() {
        return new UserAgentInterceptor(System.getProperty("http.agent"));
    }

    @Provides
    @Singleton
    String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor loggerInterceptor, UserAgentInterceptor userAgentInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        client.addInterceptor(userAgentInterceptor);
        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggerInterceptor);
        }
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String baseUrl, Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }
}