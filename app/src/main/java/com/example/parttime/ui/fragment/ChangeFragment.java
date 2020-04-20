package com.example.parttime.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.parttime.R;
import com.example.parttime.base.BaseFragment;
import com.example.parttime.entity.node.Group;
import com.example.parttime.entity.node.GroupEntity;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.UserBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.ui.adapter.GroupList2Adapter;
import com.example.parttime.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangeFragment extends BaseFragment {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private GroupList2Adapter groupList2Adapter ;
    private int userId;

    public static ChangeFragment NewInstanse(int useIrd) {
        ChangeFragment latestNewsFragment = new ChangeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userId", useIrd);
        Log.e("songbl", "新的Fragment" + latestNewsFragment.toString());
        latestNewsFragment.setArguments(bundle);
        return latestNewsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt("userId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
       unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDate(userId);
    }

    private void initView(List<GroupEntity> list) {
        groupList2Adapter = new GroupList2Adapter( list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(groupList2Adapter);
        groupList2Adapter.addChildClickViewIds(R.id.tweetDate);
        groupList2Adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                String content = null;
                switch (view.getId()) {
                    case R.id.tweetDate:
                        content = "name:" + "删除"+list.get(position).getGroupName();
                        list.remove(position);
                        break;
                    default:
                        break;
                }
               ToastUtil.showShort(content);
             groupList2Adapter.notifyDataSetChanged();
            }
        });

        groupList2Adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                new XPopup.Builder(getContext())
                        //.dismissOnBackPressed(false)
                        .autoOpenSoftInput(true)
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                        .isRequestFocus(false)
                        //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                        .asInputConfirm("修改组名", "请输入新组名", "默认", "我是默认Hint文字",
                                new OnInputConfirmListener() {
                                    @Override
                                    public void onConfirm(String text) {
                                        ToastUtil.showShort("input text: " + text);
                                    }
                                })
                        .show();
                return false;
            }
        });
    }


    private void initDate(int userId) {
        UserBean userBean = new UserBean();
        userBean.setUserId(userId);
        AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                .queryUserAndGroupAndContactsById(userBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<List<Group>>() {
                    @Override
                    public void onSuccess(List<Group> groups) {
                        List<GroupEntity> list = new ArrayList<>();
                        for (int i = 0; i < groups.size(); i++) {
                            GroupEntity groupEntity = new GroupEntity();
                            groupEntity.setGroupId(groups.get(i).getGroupId());
                            groupEntity.setGroupName(groups.get(i).getGroupName());
                            list.add(groupEntity);
                        }
                        initView(list);
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

    @OnClick({R.id.recyclerView, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recyclerView:
                break;
            case R.id.fab:
                new XPopup.Builder(getContext())
                        //.dismissOnBackPressed(false)
                        .autoOpenSoftInput(true)
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                        .isRequestFocus(false)
                        //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                        .asInputConfirm("新建组名", "请输入组名", "默认", "我是默认Hint文字",
                                new OnInputConfirmListener() {
                                    @Override
                                    public void onConfirm(String text) {
                                        ToastUtil.showShort("input text: " + text);
                                    }
                                })
                        .show();
                break;
        }
    }
}
