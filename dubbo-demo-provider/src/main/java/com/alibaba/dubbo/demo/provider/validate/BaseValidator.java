package com.alibaba.dubbo.demo.provider.validate;

import com.alibaba.dubbo.demo.api.Validator;
import com.imcbb.dubbo.demo.util.ValidatorUtil;
import org.springframework.stereotype.Service;

/**
 * @author 陈彬
 * Date 2018/9/17
 * Time 14:54
 */
@Service
public class BaseValidator implements Validator<Object> {

    @Override
    public void doValidate(Object params) throws IllegalArgumentException {
        ValidatorUtil.validateParams(params);
    }
}
