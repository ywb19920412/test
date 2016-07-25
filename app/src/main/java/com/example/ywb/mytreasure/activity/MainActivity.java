package com.example.ywb.mytreasure.activity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ywb.mytreasure.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login,R.id.btn_register})
    public void click(View view){
        int id=view.getId();
        Intent intent=null;
        switch (id){
            case R.id.btn_login:
                intent=new Intent(this,LoginActivity.class);
                break;
            case R.id.btn_register:
                intent=new Intent(this,RegisterActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
