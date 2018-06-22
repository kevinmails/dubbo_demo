package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.demo.util.TraceLogUtil;
import com.alibaba.dubbo.rpc.RpcContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.demo.api.DemoService;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Consumer implements Runnable {

    static ClassPathXmlApplicationContext context = null;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1; i++) {
            service.execute(new Consumer());

        }
        service.shutdown();

    }

    public static String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public void run() {
        TraceLogUtil.setTraceId();
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("world");
        log.info(hello);

        //第二次调用，为了验证dubbo隐式参数"setAttachment 设置的 KV 对，在完成一次远程调用会被清空，即多次远程调用要多次设置"
        String hello2 = demoService.sayHello("world2");
        log.info(hello2); // cool, how are you~
    }
}
