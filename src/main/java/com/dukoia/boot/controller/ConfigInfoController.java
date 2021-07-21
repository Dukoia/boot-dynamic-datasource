package com.dukoia.boot.controller;


import com.dukoia.boot.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * config_info 前端控制器
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/config")
public class ConfigInfoController {

    @Autowired
    IUserInfoService iUserInfoService;

    @GetMapping("/list")
    public Object list() {
        return iUserInfoService.list();
    }

    @GetMapping(value = "/string", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String string() {
        return "哈哈";
    }

    @GetMapping("/error")
    public String error() {
        int i = 1 / 0;
        return "hhhh";
    }


}

