package com.example.EAT_demo;

import java.util.List;

@SuppressWarnings("all")
public class Response <T>{
    private T data;
    private boolean success;
    private String errorMsg;
    private String token;
    private List<T> dataList;


    public static <K> Response<K> newSuccess(K data){
        Response<K> response = new Response<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }


    public static <K> Response<K> newSuccessToken(String token){
        Response<K> response = new Response<>();
        response.setSuccess(true);
        response.setToken(token);
        return response;
    }

    public static <K> Response<K> newFail(String errorMsg){
        Response<K> response = new Response<>();
        response.setErrorMsg(errorMsg);
        response.setSuccess(false);
        return response;
    }

    // 返回包含列表数据的响应
    public static <K> Response<K> newSuccessWithList(List<K> dataList) {
        Response<K> response = new Response<>();
        response.setDataList(dataList);
        response.setSuccess(true);
        return response;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
