package com.example.parttime.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.parttime.R;
import com.example.parttime.base.BaseFragment;
import com.example.parttime.entity.node.Group;
import com.example.parttime.entity.node.UserEntity;
import com.example.parttime.entity.node.tree.FirstNode;
import com.example.parttime.entity.node.tree.ThirdNode;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.UserBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.ui.activity.ContractChangeActivity;
import com.example.parttime.ui.adapter.tree.NodeTreeAdapter;
import com.example.parttime.utils.LogUtil;
import com.example.parttime.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class ContactFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.fab_group)
    FloatingActionButton fab_group;
    private Unbinder unbinder;

    //    private RecyclerView mRecyclerView;

//    private FloatingActionButton floatingActionButton;
    private NodeTreeAdapter adapter = new NodeTreeAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        initDate(UserEntity.getSingleton().getUserId());
        initView();
    }

    private void initView() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(adapter);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        //第一次被遮挡之后开始调用
        LogUtil.e("联系人", "hidden");
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            initDate(UserEntity.getSingleton().getUserId());
        }
    }




    @OnClick({R.id.rv_list, R.id.fab_group,R.id.fab_contacts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_list:
                break;
            case R.id.fab_group:
                //群组管理
                Intent intent = new Intent(getActivity(), ContractChangeActivity.class);
                intent.putExtra("userId",UserEntity.getSingleton().getUserId());
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.fab_contacts:
                Intent inten2t = new Intent(getActivity(), ContractChangeActivity.class);
                inten2t.putExtra("userId",UserEntity.getSingleton().getUserId());
                inten2t.putExtra("type",2);
                startActivity(inten2t);
                break;
        }
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
                        List<BaseNode> list = new ArrayList<>();
                        for (int i = 0; i < groups.size(); i++) {
                            List<BaseNode> secondNodeList = new ArrayList<>();
                            for (int n = 0; n < groups.get(i).getContacts().size(); n++) {

                            ThirdNode node = new ThirdNode(groups.get(i).getContacts().get(n).getContactsName(),groups.get(i).getContacts().get(n).getContactsId());
                             secondNodeList.add(node);
                            }
                            FirstNode entity = new FirstNode(secondNodeList, groups.get(i).getGroupName(),groups.get(i).getGroupId());
                            // 模拟 默认第0个是展开的
                            entity.setExpanded(i == 0);
                            list.add(entity);
                        }


                        //替换整体的node，顶级
                        adapter.setList(list);
                        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                //群组管理
                                Intent intent = new Intent(getActivity(), ContractChangeActivity.class);
                                intent.putExtra("userId",userId);
                                intent.putExtra("type",1);
                                startActivity(intent);
                                return true;
                            }
                        });

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
