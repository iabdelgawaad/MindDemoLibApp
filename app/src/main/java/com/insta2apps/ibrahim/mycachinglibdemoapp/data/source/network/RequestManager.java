package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insta2apps.ibrahim.mycachinglibdemoapp.BuildConfig;

import io.reactivex.schedulers.Schedulers;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private static RequestManager mRequestManager;

    private RequestManager() {
    }


    public static synchronized RequestManager getInstance(){
        if (mRequestManager == null){
            mRequestManager = new RequestManager();
        }
        return mRequestManager;
    }


    public RequestConfig generateRequestConfig() {
        RequestConfig commonConfig = new RequestConfig() ;

        // Allowing children classes to update the config object, couple of things are to be noted here
        // 1- This is done before adding the headers interceptor so that it includes all headers (common and child's)
        // 2- Any Converters added by children will be added first and has priority over the default converter
        // 3- Any CallAdapters added by children will be added first and has priority over default call MindAdapter
        commonConfig = updateRequestConfig(commonConfig);


        // Adding request/response logger in debug builds
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            commonConfig.addInterceptor(loggingInterceptor);
        }

        // Adding default converters and call com.insta2apps.ibrahim.minddownloaderlib.adapters

        commonConfig.addConverterFactory(GsonConverterFactory.create(getCommonGsonInstance()));

        commonConfig.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));

        return commonConfig;
    }

    private Gson getCommonGsonInstance() {
        return new GsonBuilder().create();
    }

    /**
     * Helper method for creating a Retrofit service interface implementation
     *
     * @param clazz Retrofit service interface class object
     * @param <T>   Retrofit service interface type
     * @return An implementation of Retrofit service interface
     */

    public <T> T startRequest(Class<T> clazz) {
        return new APIClient(generateRequestConfig()).createService(clazz);
    }

    public <T> T startRequest(Class<T> clazz, RequestConfig requestConfig) {
        return new APIClient(requestConfig).createService(clazz);
    }

    public RequestConfig updateRequestConfig(RequestConfig requestConfig) {
        return requestConfig;
    }

}
