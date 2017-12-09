package com.study.controller;

import com.study.common.Response;
import com.study.common.Status;
import com.study.model.Request;
import com.study.service.UserLogService;
import com.study.common.QueryBase;
import com.study.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController

public class UserLogController {

    static Logger logger = LoggerFactory.getLogger(UserLogController.class);

    @Autowired
    private UserLogService userLogService;

    @RequestMapping(value = "/user/log", method = RequestMethod.POST)
    @ResponseBody
    public Object selectUserLogPage(@RequestBody String requestString){
        Request request = null;
        try {
            request = JsonMapper.getObjectMapper().readValue(requestString, Request.class);
        } catch (IOException e) {
            logger.error("Json转换失败", e);
            return new Response(Status.ERROR, "参数格式不正确");
        }
        QueryBase queryBase = new QueryBase(request.getRequestPage(), request.getPageSize());
        Map<String, Object> parameters = new HashMap<String, Object>();
        queryBase.setParameters(parameters);
        int status = userLogService.selecPages(queryBase);
        return new Response(status, queryBase);
    }
}
