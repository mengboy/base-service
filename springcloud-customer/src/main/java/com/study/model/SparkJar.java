package com.study.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class SparkJar implements Serializable{
    private static final long serialVersionUID = -8736616045315083846L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String jarpath;  //路径
    private String sparkname; //模块名称
    private String restapi;  //REST接口
    private String mainclass;  //mainclass
    private String args;   //  参数
    private String detail;  // 说明
    private String subuser;  // 提交用户
    private String workmode;  //  工作模式
    private String subtime;   // 提交时间
    private String check;   //  审核状态
    private String version;  //版本号
    private String enable;  // 是否可用









    @Override
    public String toString() {
        return "";
/*        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                '}';*/
    }
}