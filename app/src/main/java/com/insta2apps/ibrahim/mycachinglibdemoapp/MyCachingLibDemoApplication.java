package com.insta2apps.ibrahim.mycachinglibdemoapp;

import android.app.Application;

import com.insta2apps.ibrahim.mycachinglibdemoapp.di.components.ApplicationComponent;
import com.insta2apps.ibrahim.mycachinglibdemoapp.di.components.DaggerApplicationComponent;
import com.insta2apps.ibrahim.mycachinglibdemoapp.di.modules.ApplicationModule;

/**
 * Created by Ibrahim AbdelGawad on 4/9/2018.
 */

public class MyCachingLibDemoApplication extends Application {
    private static MyCachingLibDemoApplication instance;
    protected ApplicationComponent daggerComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initDagger();
    }

    private void initDagger() {
        daggerComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static MyCachingLibDemoApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getDaggerComponent() {
        return daggerComponent;
    }
}
