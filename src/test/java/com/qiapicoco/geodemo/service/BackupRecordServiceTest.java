package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.DataBackupRecord;
import com.qiapicoco.geodemo.repository.DataBackupRecordRepository;
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
    private DataBackupRecordRepository dataBackupRecordRepository;

    @InjectMocks
    private BackupRecordService backupRecordService = new BackupRecordServiceImpl();

    @Test
    public void testSaveBackupRecord() {
        DataBackupRecord record = new DataBackupRecord();
        record.setBackupType("Full Backup");
        when(dataBackupRecordRepository.save(record)).thenReturn(record);

        DataBackupRecord savedRecord = backupRecordService.saveBackupRecord(record);
        assertEquals("Full Backup", savedRecord.getBackupType());
        verify(dataBackupRecordRepository, times(1)).save(record);
    }

    @Test
    public void testGetBackupRecordById() {
        DataBackupRecord record = new DataBackupRecord();
        record.setBackupId(1);
        record.setBackupType("Full Backup");
        when(dataBackupRecordRepository.findById(1)).thenReturn(Optional.of(record));

        DataBackupRecord foundRecord = backupRecordService.getBackupRecordById(1);
        assertEquals("Full Backup", foundRecord.getBackupType());
        verify(dataBackupRecordRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllBackupRecords() {
        DataBackupRecord record1 = new DataBackupRecord();
        record1.setBackupType("Full Backup");
        DataBackupRecord record2 = new DataBackupRecord();
        record2.setBackupType("Incremental Backup");
        List<DataBackupRecord> records = Arrays.asList(record1, record2);
        when(dataBackupRecordRepository.findAll()).thenReturn(records);

        List<DataBackupRecord> allRecords = backupRecordService.getAllBackupRecords();
        assertEquals(2, allRecords.size());
        verify(dataBackupRecordRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteBackupRecord() {
        doNothing().when(dataBackupRecordRepository).deleteById(1);
        backupRecordService.deleteBackupRecord(1);
        verify(dataBackupRecordRepository, times(1)).deleteById(1);
    }
}