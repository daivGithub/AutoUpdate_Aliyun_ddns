package com.wqw.cloudresolution.controller;

import com.wqw.cloudresolution.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private JobService jobService;

    @RequestMapping("/")
    public String hello(){
        return "111";
    }
}