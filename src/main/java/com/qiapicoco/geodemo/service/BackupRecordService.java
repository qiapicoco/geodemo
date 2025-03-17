package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.BackupRecord;

import java.util.List;

public interface BackupRecordService {

    /**
     * 保存数据备份记录
     * @param record 数据备份记录实体
     * @return 保存后的记录
     */
    BackupRecord saveBackupRecord(BackupRecord record);

    /**
     * 根据 ID 获取数据备份记录
     * @param backupId 记录 ID
     * @return 对应的记录，如果不存在则返回 null
     */
    BackupRecord getBackupRecordById(Integer backupId);

    /**
     * 获取所有数据备份记录
     * @return 所有记录的列表
     */
    List<BackupRecord> getAllBackupRecords();

    /**
     * 根据 ID 删除数据备份记录
     * @param backupId 记录 ID
     */
    void deleteBackupRecord(Integer backupId);
}