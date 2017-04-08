package com.magicdroid.magictrip.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.magicdroid.magictrip.R;
import com.magicdroid.magictrip.network.APIServices;
import com.magicdroid.magictrip.network.RetroFitServiceCreator;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}
