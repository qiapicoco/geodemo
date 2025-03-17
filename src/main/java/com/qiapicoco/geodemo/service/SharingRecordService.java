package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.DataSharingRecord;

import java.util.List;

public interface SharingRecordService {

    /**
     * 保存数据共享记录
     * @param record 数据共享记录实体
     * @return 保存后的记录
     */
    DataSharingRecord saveSharingRecord(DataSharingRecord record);

    /**
     * 根据 ID 获取数据共享记录
     * @param recordId 记录 ID
     * @return 对应的记录，如果不存在则返回 null
     */
    DataSharingRecord getSharingRecordById(Integer recordId);

    /**
     * 获取所有数据共享记录
     * @return 所有记录的列表
     */
    List<DataSharingRecord> getAllSharingRecords();

    /**
     * 根据 ID 删除数据共享记录
     * @param recordId 记录 ID
     */
    void deleteSharingRecord(Integer recordId);
}