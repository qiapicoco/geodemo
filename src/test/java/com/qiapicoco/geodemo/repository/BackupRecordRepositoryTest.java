package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.BackupRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BackupRecordRepositoryTest {

    @Autowired
    private BackupRecordRepository backupRecordRepository;

    @Test
    public void testSaveBackupRecord() {
        BackupRecord record = new BackupRecord();
        record.setBackupType("Full Backup");
        BackupRecord savedRecord = backupRecordRepository.save(record);
        assertEquals("Full Backup", savedRecord.getBackupType());
    }

    @Test
    public void testGetBackupRecordById() {
        BackupRecord record = new BackupRecord();
        record.setBackupType("Full Backup");
        BackupRecord savedRecord = backupRecordRepository.save(record);
        Optional<BackupRecord> foundRecord = backupRecordRepository.findById(savedRecord.getBackupId());
        assertTrue(foundRecord.isPresent());
        assertEquals("Full Backup", foundRecord.get().getBackupType());
    }
}