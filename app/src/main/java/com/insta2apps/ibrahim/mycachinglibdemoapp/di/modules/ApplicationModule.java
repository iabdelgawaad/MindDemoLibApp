package com.insta2apps.ibrahim.mycachinglibdemoapp.di.modules;

import android.app.Application;
import android.content.Context;

import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network.RequestManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */
@Module
public class ApplicationModule {
    private Application myApplication;

    public ApplicationModule(Application myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return myApplication;
    }

    @Provides
    @Named("executor_thread")
    io.reactivex.Scheduler provideExecutorThread() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    io.reactivex.Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    RequestManager provideRequestManager() {
        return RequestManager.getInstance();
    }

}
