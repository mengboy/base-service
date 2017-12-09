package com.study.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.pagehelper.PageInfo;
import com.study.common.QueryBase;
import com.study.datainfo.ConstString;
import com.study.model.CountEntity;
import com.study.service.DataService;
import com.study.model.DataEntity;
import com.study.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {
    @Resource
    private DataService dataService;

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Registration registration;

    @RequestMapping("/data")
    @ResponseBody
    public Object getAll(@RequestBody String data){
        System.out.println(data);
        Map<String, Integer> params = null;
        try {
            params = JsonMapper.getObjectMapper().readValue(data, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if(params ==  null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        DataEntity dataEntity = new DataEntity();
        PageInfo<DataEntity> pageInfo = dataService.selectPage(dataEntity, params.get("start"), params.get("length"));
        System.out.println("pageInfo total " + pageInfo.getTotal());
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        map.put("tableHead", ConstString.getStrs());
        return map;
    }


    @RequestMapping("/count")
    @ResponseBody
    public Object count(@RequestBody String req){
        Map<String, String> reqMap = null;
        try{
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> result = new ArrayList<>();
        if(reqMap != null){
            for(Map.Entry<String, String> entry : reqMap.entrySet()){
                Map<String, Object> map = new HashMap<>();
                map.put(entry.getKey(), entry.getValue());
                QueryBase queryBase = new QueryBase(map);
                List<CountEntity> countEntities = dataService.countMap(queryBase);
                Map<String, Object> timMap = new HashMap<>();
                timMap.put(entry.getKey(), countEntities);
                result.add(timMap);
            }
        }
        resultMap.put("result", result);
        return resultMap;
    }

    @RequestMapping("/service")
    @ResponseBody
    public Object serviceInstance() {
        return discoveryClient.getServices();

//        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
//        if (list != null && list.size() > 0) {
//            return list.get(0);
//        }
//        return null;
//
    }
}
