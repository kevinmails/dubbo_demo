package com.imcbb.dubbo.demo.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KEVIN
 */
public class DubboProviderStarter {
    public static ClassPathXmlApplicationContext context;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        context.start();
        System.in.read();
    }

}
