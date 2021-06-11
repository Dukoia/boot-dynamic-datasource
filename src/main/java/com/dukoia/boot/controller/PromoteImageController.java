package com.dukoia.boot.controller;


import com.dukoia.boot.model.PromoteImageDO;
import com.dukoia.boot.service.PromoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/promote")
public class PromoteImageController {

    @Autowired
    PromoteImageService promoteImageService;

    @GetMapping("list")
    public Object getList(){
        return promoteImageService.list();
    }

    @GetMapping("put")
    public Object put(){
        PromoteImageDO picoPromoteImageDO = new PromoteImageDO();
        picoPromoteImageDO.setImageUrl("http://hello.com");
        picoPromoteImageDO.setSubjectName("master");
        picoPromoteImageDO.setCreateUser("dukoia");
        return promoteImageService.save(picoPromoteImageDO);
    }
}

