package com.insta2apps.ibrahim.mycachinglibdemoapp.data.repository;

import android.support.annotation.NonNull;

import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.UserModel;
import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network.API;
import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.network.RequestManager;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ibrahim AbdelGawad on 4/9/2018.
 */

public class UserRepository {
    private final RequestManager requestManager;

    @Inject
    public UserRepository(@NonNull RequestManager requestManager) {
        this.requestManager = requestManager;
    }

   public Observable<UserModel> loadUserListRemotely() {
        return requestManager.startRequest(API.class).getUserList();
    }
}
