package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.demo.api.LoginService;
import com.alibaba.dubbo.demo.api.bean.Fruit;
import com.alibaba.dubbo.demo.util.TraceLogUtil;
import com.alibaba.dubbo.rpc.RpcContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.dubbo.demo.api.DemoService;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer2 implements Runnable {
    private static Log LOG = LogFactory.getLog(Consumer2.class);

    static ClassPathXmlApplicationContext context = null;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1; i++) {
            service.execute(new Consumer2());

        }
        service.shutdown();

    }

    public static String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public void run() {
        String traceId = getTraceId();
        TraceLogUtil.setTraceId(traceId);
        DemoService demoService = (DemoService) context.getBean("demoService");
        LoginService loginService = (LoginService) context.getBean("loginService");

        Fruit fruit = demoService.getColor(new Fruit("Apple"));
        LOG.info("Apple's  color is:" + fruit.getColor());

        RpcContext.getContext().setAttachment("traceId", traceId);
        boolean loggedIn = loginService.doLogin("kevin", "123456");
        LOG.info("kevin is logged in:" + loggedIn);
    }
}
