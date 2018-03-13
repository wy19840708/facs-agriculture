package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.UserMapper;
import com.facs.agriculture.iservice.ILoginService;
import com.facs.agriculture.support.model.po.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 登录服务实现
 * @Author luke
 * @Date 2018-02-02
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String code, String password) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(password))
            return false;
        User user = userMapper.loadByCode(code);
        if (password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}

