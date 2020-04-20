package com.example.parttime.net.bean;

public class LoginBean {

    private String mobile;
    private  String password;

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
    public String toString() {
        return "LoginBean{" +
                "username='" + mobile + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
