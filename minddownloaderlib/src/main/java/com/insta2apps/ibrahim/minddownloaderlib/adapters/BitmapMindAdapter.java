package com.insta2apps.ibrahim.minddownloaderlib.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.MindAdapter;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public class BitmapMindAdapter implements MindAdapter<byte[], Bitmap> {
    @Override
    public Bitmap adapt(byte[] source) throws Exception {
        return BitmapFactory.decodeByteArray(source, 0, source.length);
    }
}
