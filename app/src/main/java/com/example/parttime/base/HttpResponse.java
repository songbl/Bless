package com.example.parttime.base;


public class HttpResponse<T> {


    @Override
    public String toString() {
        return "HttpResponse{" +
                "code='" + code + '\'' +
                ", result=" + result +
                ", msg=" + msg +
                '}';
    }

    /**
     * code : 1
     * result : null
     * error : {"message":"请登陆后再访问！","data":null}
     */

    private String code;
    private T result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * message : 请登陆后再访问！
     * data : null
     */

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }




    public boolean isSuccessful(){
        return "0".equals(code);
    }
}
