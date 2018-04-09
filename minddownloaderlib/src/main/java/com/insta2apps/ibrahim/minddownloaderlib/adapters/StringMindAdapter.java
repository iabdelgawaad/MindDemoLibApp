package com.insta2apps.ibrahim.minddownloaderlib.adapters;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.MindAdapter;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public class StringMindAdapter implements MindAdapter<byte[], String> {
    @Override
    public String adapt(byte[] source) throws Exception {
        return new String(source);
    }
}
