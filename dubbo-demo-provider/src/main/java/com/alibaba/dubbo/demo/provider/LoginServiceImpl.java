package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.api.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public boolean doLogin(String username, String password) {
        return true;
    }
}
