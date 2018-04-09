package com.insta2apps.ibrahim.minddownloaderlib.adapters;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.MindAdapter;

import org.json.JSONObject;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public class JsonMindAdapter implements MindAdapter<byte[], JSONObject> {
    private StringMindAdapter stringAdapter = new StringMindAdapter();

    @Override
    public JSONObject adapt(byte[] source) throws Exception {
        String string = stringAdapter.adapt(source);
        return new JSONObject(string);
    }
}
