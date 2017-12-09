package com.study.service;

import com.github.pagehelper.PageInfo;
import com.study.common.QueryBase;
import com.study.model.CountEntity;
import com.study.model.DataEntity;

import java.util.List;
import java.util.Map;

public interface DataService {
    PageInfo<DataEntity> selectPage(DataEntity dataEntity, int start, int length);

    List<CountEntity> countMap(QueryBase attr);
}
