package com.insta2apps.ibrahim.minddownloaderlib.interfaces;

import android.graphics.Bitmap;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public interface Cache<T> {
    T pop(String key);
    void set(String key, T value);
    void setMaximumCacheSize(int size);
    int getMaximumCacheSize();
}
