package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.DataSharingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataSharingRecordRepository extends JpaRepository<DataSharingRecord, Long> {

    /**
     * 根据共享对象查找数据共享记录
     *
     * @param sharingObject 共享对象
     * @return 符合条件的数据共享记录列表
     */
    List<DataSharingRecord> findBySharingObject(String sharingObject);
}