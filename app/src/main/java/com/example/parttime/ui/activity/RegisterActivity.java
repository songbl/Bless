package com.example.parttime.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.parttime.R;
import com.example.parttime.base.BaseActivity;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.RegisterBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.utils.LogUtil;
import com.example.parttime.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RegisterActivity extends BaseActivity {


    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.rb_boy)
    RadioButton rbBoy;
    @BindView(R.id.rb_girl)
    RadioButton rbGirl;
    @BindView(R.id.rd_gender)
    RadioGroup rdGender;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.content)
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }
//    String cardType = rdCardSelf.isChecked() ? "0" : "1";


    /**
     * {
     * 	"username": "吴奇隆",
     * 	"mobile": "13336371247",
     * 	"password": "123456",
     * 	"gender": 0
     * }
     * @param view
     */
    @OnClick({R.id.et_account, R.id.et_password, R.id.et_nickname, R.id.rb_boy, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_account:
                break;
            case R.id.et_password:
                break;
            case R.id.et_nickname:
                break;
            case R.id.rb_boy:
                break;
            case R.id.btn_register:
                int gender = rbGirl.isChecked() ? 0 : 1;
                RegisterBean register = new RegisterBean();
                register.setUsername(etNickname.getText().toString());
                register.setPassword(etPassword.getText().toString());
                register.setMobile(etAccount.getText().toString());
                register.setGender(gender);

                loadingPopup.show();
                AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                        .register(register)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CallBackWrapper<Boolean>() {
                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                LogUtil.e("RegisterActivity","onSuccess(RegisterActivity.java:91)"+aBoolean.toString());
                                 loadingPopup.dismiss();
                                 ToastUtil.showShort("注册成功");
                                 finish();
                            }

                            @Override
                            public void onError(String msg, int code) {
                                loadingPopup.dismiss();
                                ToastUtil.showShort("失败"+msg);
                                LogUtil.e("RegisterActivity","onError(RegisterActivity.java:96)"+msg);
                            }
                        });
                break;
        }
    }
}
