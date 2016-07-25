package com.example.ywb.mytreasure.activity;

import android.content.ContentProvider;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywb.mytreasure.R;
import com.example.ywb.mytreasure.entity.RegisterResult;
import com.example.ywb.mytreasure.entity.User;
import com.example.ywb.mytreasure.util.ActivityUtils;
import com.example.ywb.mytreasure.util.RegexUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.et_username) EditText etUsername;
    @Bind(R.id.et_password) EditText etPassword;
    @Bind(R.id.et_confirm) EditText etConfirm;
    @Bind(R.id.toolBar) Toolbar toolbar;
    @Bind(R.id.btn_register) Button btn_register;
    private ActivityUtils activityUtils;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_register);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        // 用toolbar来更换以前的actionBar
        setSupportActionBar(toolbar);
        // 激活Home(左上角,内部使用的选项菜单处理的),设置其title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.register);
        }
        etUsername.addTextChangedListener(mTextWatcher);// EditText监听
        etPassword.addTextChangedListener(mTextWatcher);// EditText监听
        etConfirm.addTextChangedListener(mTextWatcher);// EditText监听
    }

    private String username;
    private String password;

    // 选项菜单处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            String confirm = etConfirm.getText().toString();
            boolean canRegister = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)
                    && password.equals(confirm);
            btn_register.setEnabled(canRegister);// 注意：在布局内注册按钮默认是不可用的
        }
    };

    @OnClick(R.id.btn_register)
    public void register() {
        activityUtils.hideSoftKeyboard();
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(getString(R.string.username_rules));
            return;
        }
        if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(getString(R.string.password_rules));
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("treasure/gson");
        User user = new User(username, password);
        gson=new Gson();
        String str = gson.toJson(user);
        RequestBody requestBody  = RequestBody.create(mediaType,str);
        final Request request = new Request.Builder()
                .url("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
                .post(requestBody)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activityUtils.showToast("请求失败，请检查你的网络");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result=response.body().string();
                    handler.obtainMessage().sendToTarget();
                    Log.e("sads",result);
                    RegisterResult registerResult=gson.fromJson(result,RegisterResult.class);
                    Log.e("aaaaaaa",registerResult.getMsg());
                    Looper.prepare();
                    activityUtils.showToast(registerResult.getMsg());
                    if (registerResult.getCode()==1){
                        activityUtils.startActivity(LoginActivity.class);
                    }
                    Looper.loop();
                }
            }
        });

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
