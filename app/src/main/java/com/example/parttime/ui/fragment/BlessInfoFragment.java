package com.example.parttime.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parttime.R;
import com.example.parttime.base.BaseFragment;
import com.example.parttime.entity.node.InfoMail;
import com.example.parttime.entity.node.UserEntity;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.UserBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.ui.adapter.InfoMailAdapter;
import com.example.parttime.utils.LogUtil;
import com.example.parttime.utils.ToastUtil;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BlessInfoFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private InfoMailAdapter infoMailAdapter;


    public static BlessInfoFragment NewInstanse() {
        BlessInfoFragment latestNewsFragment = new BlessInfoFragment();
        return latestNewsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bless_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        initDate(UserEntity.getSingleton().getUserId());
    }


    private void initView(List<InfoMail> list) {
        infoMailAdapter = new InfoMailAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(infoMailAdapter);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //界面可见
            LogUtil.e("BlessInfoFragment", "可见");
            initDate(UserEntity.getSingleton().getUserId());
        } else {
            //界面不可见 相当于onPause
            LogUtil.e("BlessInfoFragment", "不可见");
        }
    }


    private void initDate(int userId) {
        UserBean userBean = new UserBean();
        userBean.setUserId(userId);
        AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                .getBlessInfos(userBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<List<InfoMail>>() {
                    @Override
                    public void onSuccess(List<InfoMail> infoMessages) {
                        initView(infoMessages);
                        LogUtil.e("BlessInfoFragment","onSuccess(BlessInfoFragment.java:89)"+infoMessages);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUtil.showShort(msg);

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//视图销毁时必须解绑
    }
}
