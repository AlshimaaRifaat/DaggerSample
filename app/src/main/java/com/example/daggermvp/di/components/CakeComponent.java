package com.example.daggermvp.di.components;

import com.example.daggermvp.modules.home.MainActivity;
import com.example.daggermvp.di.module.CakeModule;
import com.example.daggermvp.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = CakeModule.class, dependencies = ApplicationComponent.class)
public interface CakeComponent {

    void inject(MainActivity activity);
}