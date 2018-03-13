package com.facs.agriculture.iservice;

/**
 * 登录服务
 */
public interface ILoginService {
    //登录
    public boolean login(String code, String password);
}
