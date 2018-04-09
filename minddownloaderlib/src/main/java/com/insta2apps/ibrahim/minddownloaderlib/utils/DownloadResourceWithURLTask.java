package com.insta2apps.ibrahim.minddownloaderlib.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.MindAdapter;
import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Target;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 * <p>
 * General resource downloading class support all types (Image, pdf, ...etc)
 */

public class DownloadResourceWithURLTask<T> extends AsyncTask<String, Void, T> {

    private final Target<T> target;
    private final MindAdapter<byte[], T> mindAdapter;
    private final MyLruCache<T> cache;
    private String url;
    private Bitmap placeholder;

    public DownloadResourceWithURLTask(Target<T> target, MindAdapter<byte[], T> mindAdapter, MyLruCache<T> cache, Bitmap placeholder) {
        this.target = target;
        this.mindAdapter = mindAdapter;
        this.cache = cache;
        this.placeholder = placeholder;
    }

    @Override
    protected void onPreExecute() {
        if (placeholder != null)
            this.target.success((T) placeholder);
    }

    @Override
    protected T doInBackground(String... urls) {
        String pathToFile = urls[0];
        url = urls[0];
        byte[] ret = null;
        T adapt = null;
        try {
            InputStream in = new java.net.URL(pathToFile).openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int r = in.read(buffer);
                if (r == -1) break;
                out.write(buffer, 0, r);
            }

            ret = out.toByteArray();
            adapt = mindAdapter.adapt(ret);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return adapt;
    }

    @Override
    protected void onPostExecute(T t) {
        if (!isCancelled()) {
            target.success(t);
            cache.set(url, t);
        }
    }
}
