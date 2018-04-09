package com.insta2apps.ibrahim.minddownloaderlib.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public class Constants {


    public static int getDefaultCacheSize(Context context) {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as MyLruCache takes an
        // int in its constructor.
        if (context != null) {
            int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
            int cacheSize = 1024 * 1024 * memClass / 8;
            return cacheSize;
        } else {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            return cacheSize;
        }
    }
}
