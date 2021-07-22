package com.dukoia.boot.controller;


import com.dukoia.boot.common.BizException;
import com.dukoia.boot.common.ResponseCodeI18n;
import com.dukoia.boot.common.Result;
import com.dukoia.boot.service.IUserInfoService;
import org.apache.ibatis.annotations.Param;
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

    @GetMapping(value = "/string")
    public Result string() {
        return Result.success( "哈哈");
    }

    @GetMapping("/error")
    public String error(@Param(value = "id")Integer id) {
        if (0 == id){
            throw new BizException(ResponseCodeI18n.FAILED);
        }
        int i = 1 / 0;
        return "hhhh";
    }


}

