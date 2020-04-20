package com.example.parttime.entity.node;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Group implements Parcelable {

    protected Group(Parcel in) {
        groupId = in.readInt();
        groupName = in.readString();
        userId = in.readInt();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", userId=" + userId +
                ", userEntity=" + userEntity +
                ", contacts=" + contacts +
                '}';
    }

    /**
     * groupId : 1
     * groupName : 亲戚
     * userId : 0
     * contacts : [{"contactsId":1,"contactsName":"小龙","contactsMobile":"13336371247"},{"contactsId":2,"contactsName":"小娜","contactsMobile":"15562006399"}]
     * userEntity : {"userId":1,"username":"小龙","mobile":null,"password":null,"gender":0,"createTime":null}
     */

    private int groupId;
    private String groupName;
    private int userId;
    private UserEntityBean userEntity;
    private List<ContactsBean> contacts;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserEntityBean getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntityBean userEntity) {
        this.userEntity = userEntity;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(groupId);
        dest.writeString(groupName);
        dest.writeInt(userId);
    }

    public static class UserEntityBean {
        /**
         * userId : 1
         * username : 小龙
         * mobile : null
         * password : null
         * gender : 0
         * createTime : null
         */

        private int userId;
        private String username;
        private Object mobile;
        private Object password;
        private int gender;
        private Object createTime;

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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "UserEntityBean{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", mobile=" + mobile +
                    ", password=" + password +
                    ", gender=" + gender +
                    ", createTime=" + createTime +
                    '}';
        }
    }

    public static class ContactsBean {
        /**
         * contactsId : 1
         * contactsName : 小龙
         * contactsMobile : 13336371247
         */

        private int contactsId;
        private String contactsName;
        private String contactsMobile;

        public int getContactsId() {
            return contactsId;
        }

        public void setContactsId(int contactsId) {
            this.contactsId = contactsId;
        }

        public String getContactsName() {
            return contactsName;
        }

        public void setContactsName(String contactsName) {
            this.contactsName = contactsName;
        }

        public String getContactsMobile() {
            return contactsMobile;
        }

        public void setContactsMobile(String contactsMobile) {
            this.contactsMobile = contactsMobile;
        }

        @Override
        public String toString() {
            return "ContactsBean{" +
                    "contactsId=" + contactsId +
                    ", contactsName='" + contactsName + '\'' +
                    ", contactsMobile='" + contactsMobile + '\'' +
                    '}';
        }
    }
}
