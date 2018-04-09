package com.insta2apps.ibrahim.minddownloaderlib.interfaces;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public interface Target<T> {
    void success(T values);

    void onError(Throwable throwable);
}
