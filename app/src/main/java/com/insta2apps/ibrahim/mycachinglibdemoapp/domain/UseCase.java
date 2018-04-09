package com.insta2apps.ibrahim.mycachinglibdemoapp.domain;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

abstract class UseCase<T> {

    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    UseCase(Scheduler executorThread, Scheduler uiThread) {
        this.executorThread = executorThread;
        this.uiThread = uiThread;
        compositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> disposableObserver, Map<String, Object> map) {

        if (disposableObserver == null) {
            throw new IllegalArgumentException("observer must not be null");
        }

        final Observable<T> observable =
                this.createObservableUseCase(map).subscribeOn(executorThread).observeOn(uiThread);

        DisposableObserver observer = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createObservableUseCase(Map<String, Object> map);
}
