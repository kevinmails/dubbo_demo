package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.api.BasicService;
import com.alibaba.dubbo.demo.api.LoginService;
import com.bestpay.dubbo.demo.start.DubboProviderDemo;

/**
 * @author KEVIN
 */
public class LoginServiceImpl implements LoginService {


    @Override
    public boolean doLogin(String username, String password) {
        BasicService basicService = (BasicService) DubboProviderDemo.context.getBean("basicService");
        String s = basicService.getBaseInfo("tina");
        System.out.println(s);

        return true;
    }
}
