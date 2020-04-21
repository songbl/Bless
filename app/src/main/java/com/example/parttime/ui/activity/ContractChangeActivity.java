package com.example.parttime.ui.activity;

import android.os.Bundle;

import com.example.parttime.R;
import com.example.parttime.base.BaseActivity;
import com.example.parttime.ui.fragment.ContactsChangeFragment;
import com.example.parttime.ui.fragment.GroupChangeFragment;
import com.example.parttime.utils.LogUtil;

public class ContractChangeActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_group);
        if (savedInstanceState == null) {
//            Bundle bundle = getIntent().getExtras();
            LogUtil.e("ContractChangeActivity","onCreate(ContractChangeActivity.java:20)"+getIntent().getIntExtra("type",3));
            if (getIntent().getIntExtra("type",3)==1){
                GroupChangeFragment fragment = GroupChangeFragment.NewInstanse(getIntent().getIntExtra("userId",3));
                loadNewFragment(fragment);
            }
            if (getIntent().getIntExtra("type",3)==2){
                ContactsChangeFragment fragment = ContactsChangeFragment.NewInstanse(getIntent().getIntExtra("userId",3));
                loadNewFragment(fragment);
            }
        }
    }
}
