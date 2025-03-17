package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.BackupRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackupRecordRepository extends JpaRepository<BackupRecord, Long> {
//    public interface DataBackupRecordRepository extends JpaRepository<DataBackupRecord, Integer> {
    /**
     * 根据数据 ID 查找该数据的所有备份记录
     *
     * @param dataId 数据 ID
     * @return 备份记录列表
     */
    List<BackupRecord> findByDataDataId(Long dataId);
}