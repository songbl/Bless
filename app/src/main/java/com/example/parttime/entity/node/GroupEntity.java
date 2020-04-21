package com.example.parttime.entity.node;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupEntity {
    private int groupId;
    private String groupName;
    /**
     * userId : 0
     * contacts : [{"contactsId":1,"contactsName":"小龙","contactsMobile":"13336371247","contactsGender":0,"groupId":0},{"contactsId":2,"contactsName":"小娜","contactsMobile":"15562006399","contactsGender":0,"groupId":0}]
     * userEntity : {"userId":1,"username":"小龙","mobile":null,"password":null,"gender":0,"createTime":null}
     */

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
    }

    public static class ContactsBean {
        /**
         * contactsId : 1
         * contactsName : 小龙
         * contactsMobile : 13336371247
         * contactsGender : 0
         * groupId : 0
         */

        private int contactsId;
        private String contactsName;
        private String contactsMobile;
        private int contactsGender;
        @SerializedName("groupId")
        private int groupIdX;

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

        public int getContactsGender() {
            return contactsGender;
        }

        public void setContactsGender(int contactsGender) {
            this.contactsGender = contactsGender;
        }

        public int getGroupIdX() {
            return groupIdX;
        }

        public void setGroupIdX(int groupIdX) {
            this.groupIdX = groupIdX;
        }
    }
}
