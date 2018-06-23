package com.bestpay.dubbo.demo.filter;

import com.alibaba.dubbo.rpc.*;
import com.bestpay.dubbo.demo.util.TraceLogUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 陈彬
 * Date 2018/6/23
 * Time 11:44
 */
@Slf4j
public class ProviderTraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String  id = TraceLogUtil.getTraceId();
        log.info("ProviderTraceFilter get traceId:{}",id);
        long beginTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long endTime = System.currentTimeMillis();
        return result;
    }
}