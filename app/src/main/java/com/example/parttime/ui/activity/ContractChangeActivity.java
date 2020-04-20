package com.example.parttime.ui.activity;

import android.os.Bundle;

import com.example.parttime.R;
import com.example.parttime.base.BaseActivity;
import com.example.parttime.ui.fragment.ChangeFragment;

public class ContractChangeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_group);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            ChangeFragment fragment = ChangeFragment.NewInstanse(1);
            loadNewFragment(fragment);
        }
    }
}
