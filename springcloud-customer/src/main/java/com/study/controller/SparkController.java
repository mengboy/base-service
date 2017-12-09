package com.study.controller;

import com.github.pagehelper.PageInfo;
import com.study.model.User;
import com.study.model.UserRole;
import com.study.service.UserRoleService;
import com.study.service.UserService;
import com.study.util.PasswordHelper;
import com.study.util.SparkExecute;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/22.
 */
@RestController
@RequestMapping("/spark")
public class SparkController {


    @RequestMapping(value = "/submit")
    public String submit( ){

        SparkExecute s=new SparkExecute();
                s.sparksubmit();

        return "spark_submit";

    }

}
