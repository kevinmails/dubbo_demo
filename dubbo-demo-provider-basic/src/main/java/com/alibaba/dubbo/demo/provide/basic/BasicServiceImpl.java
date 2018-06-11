package com.alibaba.dubbo.demo.provide.basic;

import com.alibaba.dubbo.demo.api.BasicService;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author 陈彬
 * Date 2018/6/11
 * Time 8:45
 */
public class BasicServiceImpl implements BasicService {
    public String getBaseInfo(String name) {
        String traceId = "traceId:" + RpcContext.getContext().getAttachment("traceId");
        System.out.println(traceId + ",getBaseInfo called");
        return "Excellent," + name + "you called getBaseInfo";
    }
}
