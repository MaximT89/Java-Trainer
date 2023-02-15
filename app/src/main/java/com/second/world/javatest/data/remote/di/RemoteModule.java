package com.second.world.javatest.data.remote.di;

import com.second.world.javatest.data.remote.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RemoteModule {

    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}