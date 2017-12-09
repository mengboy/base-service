package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.common.QueryBase;
import com.study.model.CountEntity;
import com.study.service.DataService;
import com.study.mapper.DataEntityMapper;
import com.study.model.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service("dataService")
public class DataServiceImpl extends BaseService<DataEntity> implements DataService{

    @Autowired
    DataEntityMapper dataEntityMapper;


    @Override
    public PageInfo<DataEntity> selectPage(DataEntity dataEntity, int start, int length) {
        int page = start/length+1;
        Example example = new Example(DataEntity.class);
        //分页查询
        PageHelper.startPage(page, length);
        List<DataEntity> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public List<CountEntity> countMap(QueryBase attr) {
        List<CountEntity> countEntities = null;
        try{
            countEntities = dataEntityMapper.countMap(attr);;
        }catch (Exception e){
            e.printStackTrace();
        }
        return countEntities;
    }
}
