package com.insta2apps.ibrahim.minddownloaderlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.insta2apps.ibrahim.minddownloaderlib.adapters.BitmapMindAdapter;
import com.insta2apps.ibrahim.minddownloaderlib.interfaces.MindAdapter;
import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Target;
import com.insta2apps.ibrahim.minddownloaderlib.utils.Constants;
import com.insta2apps.ibrahim.minddownloaderlib.utils.DownloadResourceWithURLTask;
import com.insta2apps.ibrahim.minddownloaderlib.utils.MyLruCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public class MindDownLoader {

    public static class Request<T> {
        private Context context;
        private final String url;
        private final Target<T> target;
        private final MindAdapter<byte[], T> adapter;
        private MyLruCache<T> cache;
        private Drawable placeholderDrawable;
        private int placeholderResId;

        public static <T> Builder<T> get(Context context) {
            return new Builder<>(context);
        }

        public static Builder<Bitmap> getBitmap(Context context) {
            return new Builder<Bitmap>(context)
                    .adapter(new BitmapMindAdapter());
        }

        public Request(Builder<T> builder) {
            url = builder.url;
            target = builder.target;
            adapter = builder.adapter;
            cache = builder.cache;
            context = builder.context;
            placeholderDrawable = builder.placeholderDrawable;
            placeholderResId = builder.placeholderResId;
        }

        private synchronized void start() {
            Dispatcher.getInstance().dispatch(this);
        }

        public static class Builder<T> {
            private Context context;
            private String url;
            private MindAdapter<byte[], T> adapter;
            private Target<T> target;
            private MyLruCache<T> cache;
            private Drawable placeholderDrawable;
            private int placeholderResId;

            public Builder(Context context) {
                this.context = context;
            }

            public Builder load(String url) {
                this.url = url;
                return this;
            }

            public Builder<T> adapter(MindAdapter<byte[], T> adapter) {
                this.adapter = adapter;
                return this;
            }

            public Builder placeholder(Drawable placeholderDrawable) {
                this.placeholderDrawable = placeholderDrawable;
                return this;
            }

            public Builder placeholder(int placeholderResId) {
                this.placeholderResId = placeholderResId;
                return this;
            }

            public Builder<T> maximumCacheSize(int size) {
                this.cache = MyLruCache.getInstance(size);
                this.cache.setMaximumCacheSize(size);
                return this;
            }

            public void clearAll() {
                if (this.cache != null)
                    this.cache.evictAll();
            }

            public void into(Target<T> target) {
                this.target = target;
                Request<T> request = new Request<>(this);
                request.start();
            }
        }
    }

    private static class Dispatcher {
        private static final Dispatcher instance = new Dispatcher();
        private final Map<String, Request<?>> requestsMap = new HashMap<>();

        public static synchronized Dispatcher getInstance() {
            return instance;
        }

        <T> void dispatch(Request<T> request) {
            if (request.cache == null || request.cache.getMaximumCacheSize() == 0) {
                request.cache = MyLruCache.getInstance(Constants.getDefaultCacheSize(request.context));
            }
            // Check if there's already a similar request for same url
            // if not, start downloading

            //FIXME: How to cancel requests based on targets
            // cancelRequest(request.target);

            // So that it wouldn't download same URL twice
            if (requestsMap.containsKey(request.url)) {
                // Then don't download again
                // But implement a way to inform both requester
                request.target.success((T) request.cache.pop(request.url));
            } else {
                // Start AsyncTask to actually download
                requestsMap.put(request.url, request);
                if (request.placeholderDrawable == null && request.placeholderResId == 0) {
                    new DownloadResourceWithURLTask<>(request.target, request.adapter, request.cache, null)
                            .execute(request.url);
                } else {
                    new DownloadResourceWithURLTask<>(request.target, request.adapter, request.cache, getPlaceholderDrawable(request))
                            .execute(request.url);
                }
            }
        }

        void cancelRequest(Target<?> target) {
            //Search for this target in `requestsMap` and cancel it if it exists
            for (Map.Entry<String, Request<?>> entry : requestsMap.entrySet()) {
                if (entry.getValue().target.equals(target)) {
                    //...!!
                }
            }
        }

        private Bitmap getPlaceholderDrawable(Request request) {
            if (request.placeholderResId != 0) {
                return BitmapFactory.decodeResource(request.context.getResources(),
                        request.placeholderResId);
            } else {
                return ((BitmapDrawable) request.placeholderDrawable).getBitmap();
            }
        }
    }
}
