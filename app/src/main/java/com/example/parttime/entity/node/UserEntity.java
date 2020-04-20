package com.example.parttime.entity.node;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.parttime.utils.LogUtil;


public class UserEntity implements Parcelable {


    private volatile static UserEntity singleton;  //1:volatile修饰

    private UserEntity(){}


    protected UserEntity(Parcel in) {
        userId = in.readInt();
        username = in.readString();
        mobile = in.readString();
        password = in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    public static UserEntity getSingleton() {
        if (singleton == null) {  //2:减少不要同步，优化性能
            synchronized (UserEntity.class) {  // 3：同步，线程安全
                if (singleton == null) {
                    singleton = new UserEntity();  //4：创建singleton 对象
                }
            }
        }
        return singleton;
    }

    public static void updatePropertyValues(UserEntity bean){
        LogUtil.e("更新单例值");
        singleton = bean ;
    }

    private int  userId ;
    private String  username ;
    private String  mobile ;
    private String  password ;

    public static void setSingleton(UserEntity singleton) {
        UserEntity.singleton = singleton;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(username);
        dest.writeString(mobile);
        dest.writeString(password);
    }
}
