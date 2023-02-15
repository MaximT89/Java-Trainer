package com.second.world.javatest.core_di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}