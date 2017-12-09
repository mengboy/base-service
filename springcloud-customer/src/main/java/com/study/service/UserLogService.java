package com.study.service;

import com.study.model.UserLog;
import com.study.common.QueryBase;

import java.util.List;

public interface UserLogService {

    public int updateLog (UserLog userLog);

    public int insertLog(UserLog userLog);

    public int delLog(Integer userLogId);

    public int selecPages(QueryBase queryBase);
}
