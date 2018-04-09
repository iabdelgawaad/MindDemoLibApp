package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network;

import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.UserModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {
    @GET("raw/wgkJgazE")
    Observable<UserModel> getUserList();
}
