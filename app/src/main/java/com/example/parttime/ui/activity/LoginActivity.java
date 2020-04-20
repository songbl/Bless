package com.example.parttime.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parttime.R;
import com.example.parttime.base.BaseActivity;
import com.example.parttime.entity.node.UserEntity;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.LoginBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.utils.LogUtil;
import com.example.parttime.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.et_account)
    EditText etUsername;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.et_account, R.id.et_password, R.id.btn_login,R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_account:
                break;
            case R.id.et_password:
                break;
            case R.id.tv_register:
                gotoActivity(RegisterActivity.class);
                break;
            case R.id.btn_login:
                LoginBean loginBean = new LoginBean();
                loginBean.setMobile(etUsername.getText().toString());
                loginBean.setPassword(etPassword.getText().toString());

//                loginBean.setMobile("15562006399");
//                loginBean.setPassword("123456");
                loadingPopup.show();
                AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                        .loginWithPassword(loginBean)
                        .subscribeOn(Schedulers.io())

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CallBackWrapper<UserEntity>() {
                            @Override
                            public void onSuccess(UserEntity user) {
                                loadingPopup.dismiss();
                                LogUtil.e("LoginActivity", "onSuccess(LoginActivity.java:59)" + user.toString());
                                UserEntity.updatePropertyValues(user);
                                gotoActivity(MainActivity.class);
                            }

                            @Override
                            public void onError(String msg, int code) {
                                LogUtil.e("LoginActivity","onError(LoginActivity.java:80)"+msg);
//                                loadingPopup.dismiss();
                              ToastUtil.showShort(msg);
                            }
                        });
                break;
        }
    }


}
