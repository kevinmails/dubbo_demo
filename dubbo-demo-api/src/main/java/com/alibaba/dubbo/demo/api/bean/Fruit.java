package com.alibaba.dubbo.demo.api.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author kevin
 */
@Data
@AllArgsConstructor
public class Fruit implements Serializable {
    private static final long serialVersionUID = -3053414590365211252L;

    @NotEmpty(message = "名字不能为空")
    @NotBlank(message = "名字不能只含空格")
    private String name;
    private String color;

}

