package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.DataBackupRecord;

import java.util.List;

public interface BackupRecordService {

    /**
     * 保存数据备份记录
     * @param record 数据备份记录实体
     * @return 保存后的记录
     */
    DataBackupRecord saveBackupRecord(DataBackupRecord record);

    /**
     * 根据 ID 获取数据备份记录
     * @param backupId 记录 ID
     * @return 对应的记录，如果不存在则返回 null
     */
    DataBackupRecord getBackupRecordById(Integer backupId);

    /**
     * 获取所有数据备份记录
     * @return 所有记录的列表
     */
    List<DataBackupRecord> getAllBackupRecords();

    /**
     * 根据 ID 删除数据备份记录
     * @param backupId 记录 ID
     */
    void deleteBackupRecord(Integer backupId);
}