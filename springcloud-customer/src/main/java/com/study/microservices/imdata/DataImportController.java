package com.study.microservices.imdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.common.Response;
import com.study.common.Status;
import com.study.controller.UserLogController;
import com.study.model.Request;
import com.study.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/microservices")
public class DataImportController {
    static Logger logger = LoggerFactory.getLogger(UserLogController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/data")
    @ResponseBody
    public Object getData(@RequestBody String requestString){
        System.out.println(requestString);
        Request request = null;
        try {
            request = JsonMapper.getObjectMapper().readValue(requestString, Request.class);
        } catch (IOException e) {
            logger.error("Json转换失败", e);
            return new Response(Status.ERROR, "参数格式不正确");
        }
        String url = "http://" + request.getServiceName() + "/data";
        MultiValueMap<String, Object> multiMap = new LinkedMultiValueMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("start", (request.getRequestPage() - 1) * request.getPageSize());
        map.put("length", request.getPageSize());
        String json = null;
        try {
            json = JsonMapper.getObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return new Response(Status.ERROR, "请求参数转换失败");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<Object>(json, headers);
        Map<String, Object> result = null;
        try{
            result = restTemplate.postForEntity(url, httpEntity, Map.class).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return new Response(Status.ERROR, "获取数据失败");
        }
        result.put("status", 0);
        return result;
    }

    @RequestMapping("/dataCount")
    @ResponseBody
    public Object dataCount(@RequestBody String req){
        Map<String, String> reqMap = null;
        try {
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
        } catch (IOException e) {
            logger.error("Json转换失败", e);
            return new Response(Status.ERROR, "参数格式不正确");
        }
        String url = "http://" + reqMap.get("serviceName") + "/count";
        reqMap.remove("serviceName");
        HttpEntity httpEntity = new HttpEntity<Object>(reqMap);
        Map<String, Object> stringObjectMap = null;
        try{
            stringObjectMap = restTemplate.postForEntity(url, httpEntity, Map.class).getBody();
        }catch (Exception e){
            return new Response(Status.ERROR, "获取数据失败");
        }

        stringObjectMap.put("status", 0);
        return stringObjectMap;
    }

}
