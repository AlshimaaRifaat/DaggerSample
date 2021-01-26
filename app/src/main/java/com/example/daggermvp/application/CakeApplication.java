package com.example.daggermvp.application;

import android.app.Application;

import com.example.daggermvp.di.components.ApplicationComponent;
import com.example.daggermvp.di.components.DaggerApplicationComponent;
import com.example.daggermvp.di.module.ApplicationModule;

public class CakeApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "https://gist.githubusercontent.com"))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
