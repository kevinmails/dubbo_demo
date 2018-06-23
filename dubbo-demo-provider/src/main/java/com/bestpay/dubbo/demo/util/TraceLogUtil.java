package com.bestpay.dubbo.demo.util;

import com.alibaba.dubbo.rpc.RpcContext;

import java.util.UUID;

/**
 * @author 陈彬
 * Date 2018/6/11
 * Time 17:06
 */
public class TraceLogUtil {
    private TraceLogUtil() {
    }

    public static void setTraceId() {
        RpcContext.getContext().setAttachment("traceId", getUuid());
    }

    public static void setTraceId(String traceId) {
        RpcContext.getContext().setAttachment("traceId", traceId);
    }

    public static String getTraceId() {
        return RpcContext.getContext().getAttachment("traceId");
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
