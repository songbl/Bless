package com.example.parttime.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.example.parttime.R;
import com.example.parttime.utils.LogUtil;
import com.lxj.xpopup.core.CenterPopupView;

import androidx.annotation.NonNull;

public  class ChangeDialog extends CenterPopupView {

    private OnSureClickListener onSureClickListener ;
    public ChangeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.change_contact_popup;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_commit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String name =( (EditText) findViewById(R.id.et_name)).getText().toString();
                String mobile =( (EditText) findViewById(R.id.et_mobile)).getText().toString();
               LogUtil.e("ChangeDialog","onClick(ChangeDialog.java:32)"+name+mobile);
              onSureClickListener.onClickOk(name,mobile);
                dismiss();
            }
        });
    }
    protected void onShow() {
        super.onShow();
    }



    public interface OnSureClickListener {
        /**
         * 点击确定事件
         */
        void onClickOk(String name ,String mobile);

    }

    public void setOnSureClickListener(OnSureClickListener listener) {
        this.onSureClickListener = listener;
    }
}