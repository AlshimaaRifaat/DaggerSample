package com.example.daggermvp.di.components;

import com.example.daggermvp.modules.home.MainActivity;
import com.example.daggermvp.di.module.CountryModule;
import com.example.daggermvp.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = CountryModule.class, dependencies = ApplicationComponent.class)
public interface CountryComponent {

    void inject(MainActivity activity);
}