package com.bestpay.dubbo.demo.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 陈彬
 * Date 2018/6/9
 * Time 17:50
 */
@Slf4j
@Aspect
public class ServiceAspect {

    @Pointcut("execution(* com.alibaba.dubbo.demo.api.*.*(..))")
    private void executeMethod() {
    }

    /**
     * 异常通知 连接点抛出异常后执行
     *
     * @param point
     * @param e
     */
    @AfterThrowing(value = "executeMethod()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        String traceId = "traceId:" + RpcContext.getContext().getAttachment("traceId");
        String requestIp = ",requestIp:" + RpcContext.getContext().getRemoteAddress();
        String methodName = point.getSignature().getName();
        log.error("IN," + traceId + ",method:" + methodName + requestIp + ",requestArgs:"
                + Stream.of(point.getArgs()).collect(Collectors.toList()).toString() + ",Exception" + e);
        MDC.clear();

    }

    /**
     * 最终通知
     *
     * @param point
     */
    @After("executeMethod()")
    public void doAfter(JoinPoint point) {
        MDC.clear();
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
        String methodName = "method:" + point.getTarget().getClass().getName() + "." + point.getSignature().getName();
        String traceId = "traceId:" + RpcContext.getContext().getAttachment("traceId");
        String requestIp = ",requestIp:" + RpcContext.getContext().getRemoteAddress();
        MDC.put("traceId", traceId);
        log.info("IN," + methodName + requestIp + ",requestArgs:" + Stream.of(point.getArgs()).collect(Collectors.toList()).toString());
        long beginTime = System.currentTimeMillis();
        Object response = point.proceed();
        long endTime = System.currentTimeMillis();
        log.info("OUT," + methodName + requestIp + ",used:" + (endTime - beginTime) + "ms" + ",responseArgs:" + response.toString());
        MDC.clear();
        return response;
    }
}