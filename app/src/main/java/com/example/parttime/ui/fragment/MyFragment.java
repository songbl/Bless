package com.example.parttime.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.parttime.R;
import com.example.parttime.base.BaseFragment;
import com.example.parttime.entity.node.UserEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cl_person_info)
    ConstraintLayout clPersonInfo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.cl_send_email)
    ConstraintLayout clSendEmail;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.et_send_person)
    EditText etSendPerson;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_accept)
    EditText etAccept;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.cl_time)
    ConstraintLayout clTime;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    private Unbinder unbinder;
    private TimePickerView pvTime;
    private UserEntity userEntity ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initTimePicker();
        userEntity = UserEntity.getSingleton();
        initDate();
    }



    @OnClick({R.id.tv_time, R.id.cl_time, R.id.btn_send, R.id.ll_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                break;
            case R.id.cl_time:
                pvTime.show();
                break;
            case R.id.btn_send:
                break;
            case R.id.ll_email:
                break;
        }
    }

    private void initDate(){
        tvName.setText(userEntity.getUsername());
        tvPhone.setText(userEntity.getMobile());
        tvId.setText(userEntity.getUserId()+"");
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(getActivity(), getTime(date), Toast.LENGTH_SHORT).show();
                tvTime.setText(getTime(date));

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//视图销毁时必须解绑
        Log.e("songbl ", "销毁CFragment====");
    }
}
