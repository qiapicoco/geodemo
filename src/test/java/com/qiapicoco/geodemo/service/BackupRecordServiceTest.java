package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.BackupRecord;
import com.qiapicoco.geodemo.repository.BackupRecordRepository;
import com.qiapicoco.geodemo.service.impl.BackupRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BackupRecordServiceTest {

    @Mock
    private BackupRecordRepository backupRecordRepository;

    @InjectMocks
    private BackupRecordService backupRecordService = new BackupRecordServiceImpl();

    @Test
    public void testSaveBackupRecord() {
        BackupRecord record = new BackupRecord();
        record.setBackupType("Full Backup");
        when(backupRecordRepository.save(record)).thenReturn(record);

        BackupRecord savedRecord = backupRecordService.saveBackupRecord(record);
        assertEquals("Full Backup", savedRecord.getBackupType());
        verify(backupRecordRepository, times(1)).save(record);
    }

    @Test
    public void testGetBackupRecordById() {
        BackupRecord record = new BackupRecord();
        record.setBackupId(1);
        record.setBackupType("Full Backup");
        when(backupRecordRepository.findById(1)).thenReturn(Optional.of(record));

        BackupRecord foundRecord = backupRecordService.getBackupRecordById(1);
        assertEquals("Full Backup", foundRecord.getBackupType());
        verify(backupRecordRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllBackupRecords() {
        BackupRecord record1 = new BackupRecord();
        record1.setBackupType("Full Backup");
        BackupRecord record2 = new BackupRecord();
        record2.setBackupType("Incremental Backup");
        List<BackupRecord> records = Arrays.asList(record1, record2);
        when(backupRecordRepository.findAll()).thenReturn(records);

        List<BackupRecord> allRecords = backupRecordService.getAllBackupRecords();
        assertEquals(2, allRecords.size());
        verify(backupRecordRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteBackupRecord() {
        doNothing().when(backupRecordRepository).deleteById(1);
        backupRecordService.deleteBackupRecord(1);
        verify(backupRecordRepository, times(1)).deleteById(1);
    }
}