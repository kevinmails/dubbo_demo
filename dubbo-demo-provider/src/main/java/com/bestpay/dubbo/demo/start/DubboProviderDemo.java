package com.bestpay.dubbo.demo.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProviderDemo {
    public static ClassPathXmlApplicationContext context;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-provider.xml", "spring-aspect.xml", "dubbo-demo-consumer.xml"});
        context.start();
        System.in.read();
    }

}
