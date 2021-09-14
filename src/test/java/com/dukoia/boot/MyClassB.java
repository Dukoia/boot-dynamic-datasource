package com.dukoia.boot;

/**
 * @Description: MyClassB
 * @Author: jiangze.He
 * @Date: 2021-09-03
 * @Version: v1.0
 */
public class MyClassB implements MyInterface{

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String classMethod(){
        return "MyClassB";
    }
}
