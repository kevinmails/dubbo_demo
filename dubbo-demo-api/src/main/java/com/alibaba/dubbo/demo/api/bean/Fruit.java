package com.alibaba.dubbo.demo.api.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author kevin
 */
@Data
public class Fruit implements Serializable {
    private static final long serialVersionUID = -3053414590365211252L;

    @NotNull(message = "name不能为空")
    private String name;
    private String color;

}

