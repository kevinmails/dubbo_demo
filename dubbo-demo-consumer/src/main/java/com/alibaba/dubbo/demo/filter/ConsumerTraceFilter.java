package com.alibaba.dubbo.demo.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 陈彬
 * Date 2018/6/23
 * Time 11:08
 */
@Slf4j
@Activate(order = 300000)
public class ConsumerTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String methodName = invocation.getMethodName();
        String args = Stream.of(invocation.getArguments()).collect(Collectors.toList()).toString();
        log.info("dubboIn:method:{},requestArgs:{}", methodName, args);
        long beginTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long endTime = System.currentTimeMillis();
        log.info("dubboOut:method:{},used:{}ms,responseArgs:{}", methodName, String.valueOf(endTime - beginTime), result);
        return result;
    }
}
