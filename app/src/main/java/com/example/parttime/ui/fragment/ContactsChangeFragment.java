package com.example.parttime.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.parttime.R;
import com.example.parttime.base.BaseFragment;
import com.example.parttime.entity.node.ContactsEntity;
import com.example.parttime.entity.node.Group;
import com.example.parttime.entity.node.GroupEntity;
import com.example.parttime.net.ApiService;
import com.example.parttime.net.AppHttpClient;
import com.example.parttime.net.bean.UserBean;
import com.example.parttime.net.callback.CallBackWrapper;
import com.example.parttime.ui.adapter.ContactsList2Adapter;
import com.example.parttime.ui.adapter.GroupList2Adapter;
import com.example.parttime.ui.dialog.ChangeDialog;
import com.example.parttime.utils.LogUtil;
import com.example.parttime.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
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

public class ContactsChangeFragment extends BaseFragment {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private ContactsList2Adapter contactsList2Adapter ;
    private int userId;
    List<ContactsEntity> listAll ;
    public static ContactsChangeFragment NewInstanse(int useIrd) {
        ContactsChangeFragment latestNewsFragment = new ContactsChangeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userId", useIrd);
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
        View view = inflater.inflate(R.layout.fragment_specific_contacts, container, false);
       unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDate(userId);
    }

    private void initView(List<ContactsEntity> list) {
        listAll = list ;

        contactsList2Adapter = new ContactsList2Adapter( list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(contactsList2Adapter);
        contactsList2Adapter.addChildClickViewIds(R.id.tv_delete);
        contactsList2Adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                String content = null;
                switch (view.getId()) {
                    case R.id.tv_delete:
                        content = "name:" + "删除"+list.get(position).getContactsName();
                        ContactsEntity contactsEntity = new ContactsEntity();
                        contactsEntity.setContactsId(list.get(position).getContactsId());
                        AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                                .deleteContacts(contactsEntity)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CallBackWrapper<Boolean>() {
                                    @Override
                                    public void onSuccess(Boolean b) {
                                        ToastUtil.showShort("删除联系人成功");
//                                        list.remove(position);
//                                        contactsList2Adapter.notifyDataSetChanged();
                                        initDate(userId);
                                    }

                                    @Override
                                    public void onError(String msg, int code) {
                                        ToastUtil.showShort(msg);
                                    }
                                });

                        break;
                    default:
                        break;
                }
//               ToastUtil.showShort(content);

            }
        });

        contactsList2Adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

                ChangeDialog customPopup = new ChangeDialog(getContext());
                new XPopup.Builder(getContext())
                        .autoOpenSoftInput(true)
                        .asCustom(customPopup)
                        .show();
                customPopup.setOnSureClickListener(new ChangeDialog.OnSureClickListener() {
                    @Override
                    public void onClickOk(String name, String mobile) {
                        updateContacts(name,mobile,listAll.get(position).getContactsId(),listAll.get(position).getGroupId());
                    }
                });
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
                        List<ContactsEntity> list = new ArrayList<>();
                        for (int i = 0; i < groups.size(); i++) {
                            if (groups.get(i).getContacts().size()!=0){
                                for (int j =0 ;j<groups.get(i).getContacts().size();j++){
                                    ContactsEntity contactsEntity = new ContactsEntity();
                                    contactsEntity.setContactsName(groups.get(i).getContacts().get(j).getContactsName());
                                    contactsEntity.setContactsMobile(groups.get(i).getContacts().get(j).getContactsMobile());
                                    contactsEntity.setContactsId(groups.get(i).getContacts().get(j).getContactsId());
                                    LogUtil.e("ContactsChangeFragment","onSuccess(ContactsChangeFragment.java:170)"+groups.get(i).getContacts().get(j).getGroupId());
                                    contactsEntity.setGroupId(groups.get(i).getContacts().get(j).getGroupId());
                                    list.add(contactsEntity);
                                }
                            }

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




    private  void updateContacts(String name,String mobile,int contactId,int groupId){
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setContactsId(contactId);
        contactsEntity.setContactsMobile(mobile);
        contactsEntity.setContactsName(name);
        contactsEntity.setGroupId(groupId);
        AppHttpClient.getInstance().getRetrofit().create(ApiService.class)
                .updateContacts(contactsEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallBackWrapper<Boolean>() {
                    @Override
                    public void onSuccess(Boolean b) {
                        ToastUtil.showShort("修改联系人成功");

//                        contactsList2Adapter.notifyDataSetChanged();
                                      initDate(userId);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUtil.showShort(msg);
                    }
                });
    }


}
