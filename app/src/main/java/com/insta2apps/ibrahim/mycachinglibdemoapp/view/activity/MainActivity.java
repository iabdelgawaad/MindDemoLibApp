package com.insta2apps.ibrahim.mycachinglibdemoapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.insta2apps.ibrahim.mycachinglibdemoapp.MyCachingLibDemoApplication;
import com.insta2apps.ibrahim.mycachinglibdemoapp.R;
import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.UserModel;
import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.model.Item;
import com.insta2apps.ibrahim.mycachinglibdemoapp.domain.LoadUserListUseCase;
import com.insta2apps.ibrahim.mycachinglibdemoapp.domain.UseCaseObserver;
import com.insta2apps.ibrahim.mycachinglibdemoapp.domain.error.ErrorModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    LoadUserListUseCase loadUserListUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDagger();
        loadUserListUseCase.execute(new UseCaseObserver<UserModel>() {
            @Override
            public void onSuccess(UserModel userModel) {
                List<Item> itemList = userModel.getItems();
                for (Item item : itemList)
                {
                    Log.d("ID" , item.getId());
                }
            }

            @Override
            public void onFailed(ErrorModel errorModel) {
                Log.d("Error" , errorModel.getErrorDes());
            }
        }, null);
    }

    private void initializeDagger() {
        MyCachingLibDemoApplication.getInstance().getDaggerComponent().inject(this);
    }
}
