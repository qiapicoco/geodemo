package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.SharingRecord;
import com.qiapicoco.geodemo.repository.SharingRecordRepository;
import com.qiapicoco.geodemo.service.impl.SharingRecordServiceImpl;
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
    private SharingRecordRepository sharingRecordRepository;

    @InjectMocks
    private SharingRecordService sharingRecordService = new SharingRecordServiceImpl();

    @Test
    public void testSaveSharingRecord() {
        SharingRecord record = new SharingRecord();
        // 设置记录属性
        when(sharingRecordRepository.save(record)).thenReturn(record);

        SharingRecord savedRecord = sharingRecordService.saveSharingRecord(record);
        // 验证属性
        verify(sharingRecordRepository, times(1)).save(record);
    }

    @Test
    public void testGetSharingRecordById() {
        SharingRecord record = new SharingRecord();
        record.setRecordId(1);
        // 设置记录属性
        when(sharingRecordRepository.findById(1)).thenReturn(Optional.of(record));

        SharingRecord foundRecord = sharingRecordService.getSharingRecordById(1);
        assertEquals(1, foundRecord.getRecordId());
        verify(sharingRecordRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllSharingRecords() {
        SharingRecord record1 = new SharingRecord();
        // 设置记录 1 属性
        SharingRecord record2 = new SharingRecord();
        // 设置记录 2 属性
        List<SharingRecord> records = Arrays.asList(record1, record2);
        when(sharingRecordRepository.findAll()).thenReturn(records);

        List<SharingRecord> allRecords = sharingRecordService.getAllSharingRecords();
        assertEquals(2, allRecords.size());
        verify(sharingRecordRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteSharingRecord() {
        doNothing().when(sharingRecordRepository).deleteById(1);
        sharingRecordService.deleteSharingRecord(1);
        verify(sharingRecordRepository, times(1)).deleteById(1);
    }
}