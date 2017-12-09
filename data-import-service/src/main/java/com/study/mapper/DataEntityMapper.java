package com.study.mapper;

import com.study.common.QueryBase;
import com.study.model.CountEntity;
import com.study.model.DataEntity;
import com.study.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface DataEntityMapper extends MyMapper<DataEntity>{
    List<CountEntity> countMap (QueryBase attr);
}