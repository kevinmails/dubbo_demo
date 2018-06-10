package com.alibaba.dubbo.demo.api;

import com.alibaba.dubbo.demo.api.bean.Fruit;

public interface DemoService {
    String sayHello(String var1);

    /**
     * get fruit's color
     *
     * @param fruit
     * @return
     */
    Fruit getColor(Fruit fruit);
}
