package com.bestpay.dubbo.demo.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProviderDemo {
	
	  public static void main(String[] args) throws Exception {
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-demo-provider.xml", "spring-aspect.xml"});
	        context.start();
	        System.in.read(); // 按任意键退出
	    }

}
