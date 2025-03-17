package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.DataSharingRecord;
import com.qiapicoco.geodemo.repository.DataSharingRecordRepository;
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
public class SharingRecordServiceTest {

    @Mock
    private DataSharingRecordRepository dataSharingRecordRepository;

    @InjectMocks
    private SharingRecordService sharingRecordService = new SharingRecordServiceImpl();

    @Test
    public void testSaveSharingRecord() {
        DataSharingRecord record = new DataSharingRecord();
        // 设置记录属性
        when(dataSharingRecordRepository.save(record)).thenReturn(record);

        DataSharingRecord savedRecord = sharingRecordService.saveSharingRecord(record);
        // 验证属性
        verify(dataSharingRecordRepository, times(1)).save(record);
    }

    @Test
    public void testGetSharingRecordById() {
        DataSharingRecord record = new DataSharingRecord();
        record.setRecordId(1);
        // 设置记录属性
        when(dataSharingRecordRepository.findById(1)).thenReturn(Optional.of(record));

        DataSharingRecord foundRecord = sharingRecordService.getSharingRecordById(1);
        assertEquals(1, foundRecord.getRecordId());
        verify(dataSharingRecordRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllSharingRecords() {
        DataSharingRecord record1 = new DataSharingRecord();
        // 设置记录 1 属性
        DataSharingRecord record2 = new DataSharingRecord();
        // 设置记录 2 属性
        List<DataSharingRecord> records = Arrays.asList(record1, record2);
        when(dataSharingRecordRepository.findAll()).thenReturn(records);

        List<DataSharingRecord> allRecords = sharingRecordService.getAllSharingRecords();
        assertEquals(2, allRecords.size());
        verify(dataSharingRecordRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteSharingRecord() {
        doNothing().when(dataSharingRecordRepository).deleteById(1);
        sharingRecordService.deleteSharingRecord(1);
        verify(dataSharingRecordRepository, times(1)).deleteById(1);
    }
}