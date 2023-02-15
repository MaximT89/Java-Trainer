package com.second.world.javatest.core;

import android.app.Application;

import com.second.world.javatest.core_di.AppComponent;
import com.second.world.javatest.core_di.AppModule;
import com.second.world.javatest.core_di.DaggerAppComponent;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}