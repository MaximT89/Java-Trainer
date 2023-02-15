package com.second.world.javatest.core_di;

import com.second.world.javatest.data.remote.ApiModule;
import com.second.world.javatest.ui.main_activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, BaseApiModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}