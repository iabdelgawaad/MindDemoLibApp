package com.insta2apps.ibrahim.mycachinglibdemoapp.view.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public void setHomeTitle(int toolbarId, int titleId, String title, int colorBg, int titleColor) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setBackgroundResource(colorBg);
        setSupportActionBar(toolbar);
        TextView pageTitle = (TextView) toolbar.findViewById(titleId);
        pageTitle.setText(title);
        pageTitle.setTextColor(getResources().getColor(titleColor));
        getSupportActionBar().setTitle("");
    }

}
