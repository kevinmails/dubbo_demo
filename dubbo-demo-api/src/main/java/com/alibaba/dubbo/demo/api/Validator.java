package com.alibaba.dubbo.demo.api;

/**
 * @author Administrator
 */
public interface Validator<T> {

    /**
     * 基本参数验证
     *
     * @param params
     * @throws IllegalArgumentException
     */
    void doValidate(T params) throws IllegalArgumentException;

}
