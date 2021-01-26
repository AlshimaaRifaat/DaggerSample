package com.example.daggermvp.di.module;

import com.example.daggermvp.api.CountryApiService;
import com.example.daggermvp.di.scope.PerActivity;
import com.example.daggermvp.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CountryModule {

    private MainView mView;

    public CountryModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    CountryApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(CountryApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }
}

