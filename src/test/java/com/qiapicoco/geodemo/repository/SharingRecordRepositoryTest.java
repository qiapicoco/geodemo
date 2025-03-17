package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.SharingRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SharingRecordRepositoryTest {

    @Autowired
    private SharingRecordRepository sharingRecordRepository;

    @Test
    public void testSaveDataSharingRecord() {
        SharingRecord record = new SharingRecord();
        // 设置记录属性
        SharingRecord savedRecord = sharingRecordRepository.save(record);
        // 验证属性
    }

    @Test
    public void testGetDataSharingRecordById() {
        SharingRecord record = new SharingRecord();
        // 设置记录属性
        SharingRecord savedRecord = sharingRecordRepository.save(record);
        Optional<SharingRecord> foundRecord = sharingRecordRepository.findById(savedRecord.getRecordId());
        assertTrue(foundRecord.isPresent());
        // 验证属性
    }
}