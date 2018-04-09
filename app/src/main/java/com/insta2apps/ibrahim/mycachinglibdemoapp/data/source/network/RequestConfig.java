package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network;

import android.text.TextUtils;

import com.insta2apps.ibrahim.mycachinglibdemoapp.BuildConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

public class RequestConfig {
    private static final int TIMEOUT_DEFAULT_VALUE = 20;

    private String baseUrl;

    private int connectionTimeout = TIMEOUT_DEFAULT_VALUE;
    private int readTimeout = TIMEOUT_DEFAULT_VALUE;
    private int writeTimeout = TIMEOUT_DEFAULT_VALUE;

    private Map<String, String> headers;
    private Map<String, String> params;

    private List<Interceptor> interceptors = new ArrayList<>();
    private List<Converter.Factory> converterFactories = new ArrayList<>();
    private List<CallAdapter.Factory> callAdapterFactories = new ArrayList<>();
    private boolean stopRedirection;

    /* Timeout values in seconds */
    protected static final int CONNECTION_TIMEOUT = 60;
    protected static final int READ_TIMEOUT = 60;
    protected static final int WRITE_TIMEOUT = 60;


    public RequestConfig(){
        this(BuildConfig.BASE_URL
                ,CONNECTION_TIMEOUT
                ,READ_TIMEOUT,WRITE_TIMEOUT);
    }

    public RequestConfig(String baseUrl) {
        this(baseUrl, TIMEOUT_DEFAULT_VALUE, TIMEOUT_DEFAULT_VALUE, TIMEOUT_DEFAULT_VALUE);
    }


    public RequestConfig(String baseUrl, int connectionTimeout, int readTimeout, int writeTimeout) {
        verify(baseUrl, connectionTimeout, readTimeout, writeTimeout);
        this.baseUrl = baseUrl;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }
    //endregion

    private void verify(String baseUrl, int connectionTimeout, int readTimeout, int writeTimeout) {
        if (TextUtils.isEmpty(baseUrl))
            throwInvalidParameter("Base URL cannot be null or empty!");

        if (connectionTimeout < 0)
            throwInvalidParameter("Connection timeout cannot be less than zero!");

        if (readTimeout < 0)
            throwInvalidParameter("Read timeout cannot be less than zero!");

        if (writeTimeout < 0)
            throwInvalidParameter("Write timeout cannot be less than zero!");
    }

    private void throwInvalidParameter(String msg) {
        throw new IllegalArgumentException(msg);
    }


    //region Adding custom values to network executor
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public void addConverterFactory(Converter.Factory factory) {
        converterFactories.add(factory);
    }

    public void addCallAdapterFactory(CallAdapter.Factory factory) {
        callAdapterFactories.add(factory);
    }


    //region Getters
    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    int getWriteTimeout() {
        return writeTimeout;
    }

    int getReadTimeout() {
        return readTimeout;
    }

    int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    String getBaseUrl() {
        return baseUrl;
    }

    List<CallAdapter.Factory> getCallAdapterFactories() {
        return callAdapterFactories;
    }

    List<Converter.Factory> getConverterFactories() {
        return converterFactories;
    }

    List<Interceptor> getInterceptors() {
        return interceptors;
    }

}
