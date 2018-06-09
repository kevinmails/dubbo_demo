package com.bestpay.dubbo.demo.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * @author 陈彬
 * Date 2018/6/9
 * Time 17:50
 */

@Aspect
public class DemoServiceAspect {

    @Pointcut("execution(* com.alibaba.dubbo.demo.provider.*.sayHello(..))")
    private void executeMethod() {
    }


    /**
     * 前置通知
     *
     * @param point
     */
    @Before("executeMethod()")
    public void doBefore(JoinPoint point) {

        String id = RpcContext.getContext().getAttachment("name");
//        System.out.println("call:" + id);
        //Do nothing
//        LOG.debug("execute called!");
    }


    /**
     * 最终通知
     *
     * @param point
     */
    @After("executeMethod()")
    public void doAfter(JoinPoint point) {
//        TraceContext.removeTracer();
//        LOG.debug("最终通知理完成");

    }

    /**
     * 异常通知 连接点抛出异常后执行
     *
     * @param point
     * @param e
     */
    @AfterThrowing(value = "executeMethod()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        String methodName = point.getSignature().getName();
        System.out.println("the method:" + methodName + " get Exception" + e);

    }

    /**
     * 环绕通知
     *
     * @param point
     * @return
     * @throws Throwable
     */


    @Around("executeMethod()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        String methodName = point.getSignature().getName();
        System.out.println("traceId:" + traceId + ",the method:" + methodName + " start");
        Arrays.stream(point.getArgs()).forEach(s -> System.out.println("traceId:" + traceId + ",args:" + s));

        long beginTime = System.currentTimeMillis();
        // 手动执行目标方法
        Object obj = point.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("traceId:" + traceId + ",the method:" + methodName
                + " is end used time:" + (endTime - beginTime) + "ms");
        return obj;
    }
}