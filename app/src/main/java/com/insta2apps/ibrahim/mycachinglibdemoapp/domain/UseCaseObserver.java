package com.insta2apps.ibrahim.mycachinglibdemoapp.domain;

import com.insta2apps.ibrahim.mycachinglibdemoapp.domain.error.ErrorHandler;
import com.insta2apps.ibrahim.mycachinglibdemoapp.domain.error.ErrorModel;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public abstract class UseCaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        onFailed(ErrorHandler.getErrorModel(e));
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(ErrorModel errorModel);
}