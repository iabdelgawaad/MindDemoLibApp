package com.insta2apps.ibrahim.minddownloaderlib.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Cache;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 * <p>
 * Generic caching class support all types (Image, text,..etc)
 */

public class MyLruCache<T> extends LruCache implements Cache<T> {
    private int maxSize;
    private static MyLruCache instance = null;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    private MyLruCache(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    protected int sizeOf(Object key, Object value) {
        // The cache size will be measured in kilobytes rather than
        // number of items.
        return ((Bitmap) value).getByteCount() / 1024;
    }

    public static synchronized MyLruCache getInstance(int cacheSize) {
        if (instance == null) {
            instance = new MyLruCache<>(cacheSize);
        }
        return instance;
    }

    @Override
    public T pop(String key) {
        return (T) get(key);
    }

    @Override
    public void set(String key, T value) {
        put(key, value);
    }

    @Override
    public void setMaximumCacheSize(int size) {
        this.maxSize = size;
    }

    @Override
    public int getMaximumCacheSize() {
        return maxSize;
    }

    @Override
    protected void entryRemoved(boolean evicted, Object key, Object oldValue, Object newValue) {
        if (oldValue instanceof Bitmap) {
            ((Bitmap) oldValue).recycle();
        }
    }
}
