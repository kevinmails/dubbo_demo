package com.alibaba.dubbo.demo.provider.mock;

import com.alibaba.dubbo.demo.api.DemoService;
import com.alibaba.dubbo.demo.api.bean.Fruit;

/**
 * @author kevin.chen
 * Date 2019/7/13
 * Time 13:12
 */
public class DemoServiceMock implements DemoService {
    public String sayHello(String var1) {
        return var1 + "this is mock";
    }

    public Fruit getColor(Fruit fruit) {
        return null;
    }

    public void doValidate(Object params) throws IllegalArgumentException {

    }
}
