package com.alibaba.dubbo.demo.provider.mock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author kevin.chen
 * Date 2019/7/13
 * Time 13:14
 */
public class MockStarter {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-mock-provider.xml"});
        context.start();
        System.out.println("Mock Server Running...");
        System.in.read();
    }
}
