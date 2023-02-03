package com.second.world.javatest.core;

import android.app.Application;

import com.second.world.javatest.di.AppComponent;
import com.second.world.javatest.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}