package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eureka")
public class Eureka {

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/getEurekaUrl")
    @ResponseBody
    public Object getEurekaUrl(){
        return "127.0.0.1:8888";
    }

    @RequestMapping("/getServiceName")
    @ResponseBody
    public Object getServiceName(){
        return discoveryClient.getServices();
    }
}
