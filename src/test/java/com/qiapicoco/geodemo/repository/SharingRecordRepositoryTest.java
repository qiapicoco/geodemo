package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.DataSharingRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SharingRecordRepositoryTest {

    @Autowired
    private DataSharingRecordRepository dataSharingRecordRepository;

    @Test
    public void testSaveDataSharingRecord() {
        DataSharingRecord record = new DataSharingRecord();
        // 设置记录属性
        DataSharingRecord savedRecord = dataSharingRecordRepository.save(record);
        // 验证属性
    }

    @Test
    public void testGetDataSharingRecordById() {
        DataSharingRecord record = new DataSharingRecord();
        // 设置记录属性
        DataSharingRecord savedRecord = dataSharingRecordRepository.save(record);
        Optional<DataSharingRecord> foundRecord = dataSharingRecordRepository.findById(savedRecord.getRecordId());
        assertTrue(foundRecord.isPresent());
        // 验证属性
    }
}