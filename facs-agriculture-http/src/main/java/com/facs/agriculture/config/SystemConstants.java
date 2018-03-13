package com.facs.agriculture.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 系统常驻数据
 * @Author luke
 * @Date 2018-02-02
 */
public class SystemConstants {
    //登录用户拥有的使用权限
    private static Map<String,List<String>> userResource= new HashMap<String,List<String>>();

    public static List<String> getUserResource(String code){
        return userResource.get(code);
    }

    public void setUserResource(String code,List<String> list){
        userResource.put(code,list);
    }
}
