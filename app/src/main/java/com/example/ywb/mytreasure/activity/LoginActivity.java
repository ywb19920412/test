package com.example.ywb.mytreasure.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.ywb.mytreasure.R;
import com.example.ywb.mytreasure.util.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/14.
 */
public class LoginActivity extends AppCompatActivity{
    @Bind(R.id.toolBar) Toolbar toolbar;
    @Bind(R.id.et_username) EditText etUsername;
    @Bind(R.id.et_password) EditText etPassword;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils=new ActivityUtils(this);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        // 用toolbar来更换以前的actionBar
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.login);
        }
    }
    @OnClick(R.id.btn_login)
    public void login(){
        activityUtils.startActivity(MainActivity.class);
    }
}
