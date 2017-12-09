package com.study.service.impl;

import com.study.common.Status;
import com.study.mapper.UserLogMapper;
import com.study.model.UserLog;
import com.study.service.UserLogService;
import com.study.common.QueryBase;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserLogServcie")
public class UserLogServcieImpl implements UserLogService{

    static Logger logger = LoggerFactory.getLogger(UserLogServcieImpl.class);

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public int updateLog(UserLog userLog) {
        return userLogMapper.updateByUserIdLoginTime(userLog);
    }

    @Override
    public int insertLog(UserLog userLog) {
        int result = Status.ERROR;
        try{
           result =  userLogMapper.insertSelective(userLog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delLog(Integer userLogId) {
        return userLogMapper.deleteByPrimaryKey(userLogId);
    }

    @Override
    public int selecPages(QueryBase queryBase) {
        try{
            queryBase.setTotalRow(userLogMapper.selectAllCount());
            Map<String, Object> map = null;
            List<Map<String, Object>> mapList = new ArrayList<>();
            List<UserLog> userLogs = userLogMapper.selectPage(queryBase);
            for(UserLog userLog : userLogs){
                map = new HashMap<>();
                map.put("userLog", userLog);
                mapList.add(map);
            }
            queryBase.setResults(mapList);
            return Status.SUCCESS;
        }catch (Exception e){
            logger.error("分页查询userLog失败", e);
            return Status.ERROR;
        }

    }

}
