package com.study.mapper;

import com.study.model.UserLog;
import com.study.util.MyMapper;
import com.study.common.QueryBase;

import java.util.List;

public interface UserLogMapper extends MyMapper<UserLog>{
    int updateByUserIdLoginTime(UserLog userLog);

    Long selectAllCount();

    List<UserLog> selectPage(QueryBase queryBase);


}