package com.alibaba.dubbo.demo.provider.validate;

import com.bestpay.dubbo.demo.util.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 陈彬
 * Date 2018/9/17
 * Time 14:54
 */
@Service
public class Validator {

    public void validate(Object obj){
        ValidatorUtil.validateParams(obj);
    }
}
