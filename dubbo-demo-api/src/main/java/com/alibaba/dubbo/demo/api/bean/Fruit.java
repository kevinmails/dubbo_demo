package com.alibaba.dubbo.demo.api.bean;

import java.io.Serializable;

/**
 * @author kevin
 */
    public class Fruit implements Serializable {
    private static final long serialVersionUID = -3053414590365211252L;
    private String name;
    private String color;

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

