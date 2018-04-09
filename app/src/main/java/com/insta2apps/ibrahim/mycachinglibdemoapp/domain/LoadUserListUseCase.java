package com.insta2apps.ibrahim.mycachinglibdemoapp.domain;

import com.insta2apps.ibrahim.mycachinglibdemoapp.data.repository.UserRepository;
import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.UserModel;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Ibrahim AbdelGawad on 4/9/2018.
 */

public class LoadUserListUseCase extends UseCase<UserModel> {
    private UserRepository userRepository;

    @Inject
    LoadUserListUseCase(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, UserRepository userRepository) {
        super(executorThread, uiThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<UserModel> createObservableUseCase(Map<String, Object> map) {
        return userRepository.loadUserListRemotely();
    }
}
