package com.bestpay.dubbo.demo.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 陈彬
 * Date 2018/6/9
 * Time 17:50
 */

@Aspect
public class DemoServiceAspect {

    private static Log LOG = LogFactory.getLog(DemoServiceAspect.class);

    @Pointcut("execution(* com.alibaba.dubbo.demo.provider.*.*(..))")
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
        String traceId = "traceId:" + RpcContext.getContext().getAttachment("traceId");
        String methodName = point.getSignature().getName();
        LOG.info(traceId + ", call method:" + methodName + " start" + ",request args:" + Stream.of(point.getArgs()).collect(Collectors.toList()).toString());
        long beginTime = System.currentTimeMillis();
        Object response = point.proceed();
        long endTime = System.currentTimeMillis();
        LOG.info(traceId + ", call method:" + methodName + " over,used:" + (endTime - beginTime) + "ms" + ",response args:" + response.toString());
        return response;
    }
}