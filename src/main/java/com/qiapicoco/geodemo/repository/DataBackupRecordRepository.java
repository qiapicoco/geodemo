package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.DataBackupRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataBackupRecordRepository extends JpaRepository<DataBackupRecord, Long> {

    /**
     * 根据数据 ID 查找该数据的所有备份记录
     *
     * @param dataId 数据 ID
     * @return 备份记录列表
     */
    List<DataBackupRecord> findByDataDataId(Long dataId);
}