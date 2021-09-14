package com.dukoia.boot;

/**
 * @Description: MyClassA
 * @Author: jiangze.He
 * @Date: 2021-09-03
 * @Version: v1.0
 */
public class MyClassA implements MyInterface{

    private Integer name;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String classMethod(){
        return "MyClassA";
    }

    public int getSum(){
        return sum;
    }
}
