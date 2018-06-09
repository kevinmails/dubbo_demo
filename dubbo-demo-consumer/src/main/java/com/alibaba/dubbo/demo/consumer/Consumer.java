package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.demo.DemoService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable {

    static ClassPathXmlApplicationContext context = null;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i <2 ; i++) {
            service.execute(new Consumer());

        }
        service.shutdown();

    }

    @Override
    public void run() {

        Thread.currentThread().getId();

        RpcContext.getContext().setAttachment("name", Thread.currentThread().getName());
        DemoService demoService = (DemoService) context.getBean("demoService"); // get service invocation proxy
        String hello = demoService.sayHello("world"); // do invoke!
        System.out.println(hello); // cool, how are you~

        //DemoService demoService2 = (DemoService) context.getBean("demoService"); // get service invocation proxy
        String hello2 = demoService.sayHello("world2"); // do invoke!
        System.out.println(hello2); // cool, how are you~
    }
}
