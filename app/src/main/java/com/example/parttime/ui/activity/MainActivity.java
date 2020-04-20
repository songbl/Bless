package com.example.parttime.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.parttime.R;
import com.example.parttime.base.BaseActivity;
import com.example.parttime.ui.fragment.ContactFragment;
import com.example.parttime.ui.fragment.BlessInfoFragment;
import com.example.parttime.ui.fragment.MyFragment;
import com.example.parttime.ui.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    EasyNavigationBar navigationBar;

    private String[] tabText = {"朋友", "祝福", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index1, R.mipmap.find1, R.mipmap.me1};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noadd);
        ButterKnife.bind(this);


        fragments.add(new ContactFragment());
        fragments.add(new BlessInfoFragment());
        fragments.add(new MyFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .build();
    }

}
