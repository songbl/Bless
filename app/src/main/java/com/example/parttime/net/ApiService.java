package com.example.parttime.net;


import com.example.parttime.entity.node.ContactsEntity;
import com.example.parttime.entity.node.Group;
import com.example.parttime.entity.node.GroupEntity;
import com.example.parttime.entity.node.InfoMail;
import com.example.parttime.entity.node.UserEntity;
import com.example.parttime.net.bean.LoginBean;
import com.example.parttime.net.bean.RegisterBean;
import com.example.parttime.net.bean.UserBean;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    /**
     * 登录得
     */
    @POST("app/login")
    Observable<UserEntity> loginWithPassword(@Body LoginBean map);

    /**
     * 注册
     */
    @POST("app/register")
    Observable<Boolean> register(@Body RegisterBean map);
    /**
     * 获取所有信息
     */
    @POST("app/queryUserAndGroupAndContactsById")
    Observable<List<Group>> queryUserAndGroupAndContactsById(@Body UserBean map);


    /**
     * 获取祝福短信
     */
    @POST("app/getBlessInfos")
    Observable<List<InfoMail>> getBlessInfos(@Body UserBean map);

    /**
     *  修改组
     */
    @POST("app/updateContactsGroup")
    Observable<Boolean> updateGroup(@Body GroupEntity map);

    /**
     *  新建组
     */
    @POST("app/addContactsGroup")
    Observable<Boolean> addContactsGroup(@Body GroupEntity map);

    /**
     *  修改组
     */
    @POST("app/deleteContactsGroup")
    Observable<Boolean> deleteGroup(@Body GroupEntity map);



    /**
     *  修改联系人
     */
    @POST("app/updateContacts")
    Observable<Boolean> updateContacts(@Body ContactsEntity map);

    /**
     *  新建联系人
     */
    @POST("app/addContacts")
    Observable<Boolean> addContacts(@Body ContactsEntity map);

    /**
     *  修改联系人
     */
    @POST("app/deleteContacts")
    Observable<Boolean> deleteContacts(@Body ContactsEntity map);

}
