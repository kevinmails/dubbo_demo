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
        for (int i = 0; i < 2; i++) {
            service.execute(new Consumer());

        }
        service.shutdown();

    }

    @Override
    public void run() {

        Thread.currentThread().getId();

        RpcContext.getContext().setAttachment("name", Thread.currentThread().getName());
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("world");
        System.out.println(hello);

        //第二次调用，为了验证dubbo隐式参数"setAttachment 设置的 KV 对，在完成一次远程调用会被清空，即多次远程调用要多次设置"
        String hello2 = demoService.sayHello("world2");
        System.out.println(hello2); // cool, how are you~
    }
}
