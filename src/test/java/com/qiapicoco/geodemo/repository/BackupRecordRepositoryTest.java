package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.DataBackupRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BackupRecordRepositoryTest {

    @Autowired
    private DataBackupRecordRepository dataBackupRecordRepository;

    @Test
    public void testSaveDataBackupRecord() {
        DataBackupRecord record = new DataBackupRecord();
        record.setBackupType("Full Backup");
        DataBackupRecord savedRecord = dataBackupRecordRepository.save(record);
        assertEquals("Full Backup", savedRecord.getBackupType());
    }

    @Test
    public void testGetDataBackupRecordById() {
        DataBackupRecord record = new DataBackupRecord();
        record.setBackupType("Full Backup");
        DataBackupRecord savedRecord = dataBackupRecordRepository.save(record);
        Optional<DataBackupRecord> foundRecord = dataBackupRecordRepository.findById(savedRecord.getBackupId());
        assertTrue(foundRecord.isPresent());
        assertEquals("Full Backup", foundRecord.get().getBackupType());
    }
}