package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;

import java.util.List;

public interface QueryRecordService {

    /**
     * 保存查询记录
     * @param record 查询记录实体
     * @return 保存后的记录
     */
    QueryRecordStorage saveQueryRecord(QueryRecordStorage record);

    /**
     * 根据 ID 获取查询记录
     * @param recordId 记录 ID
     * @return 对应的记录，如果不存在则返回 null
     */
    QueryRecordStorage getQueryRecordById(Integer recordId);

    /**
     * 获取所有查询记录
     * @return 所有记录的列表
     */
    List<QueryRecordStorage> getAllQueryRecords();

    /**
     * 根据 ID 删除查询记录
     * @param recordId 记录 ID
     */
    void deleteQueryRecord(Integer recordId);
}