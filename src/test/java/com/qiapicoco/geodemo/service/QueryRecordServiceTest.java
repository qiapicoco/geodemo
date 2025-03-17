package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import com.qiapicoco.geodemo.repository.QueryRecordStorageRepository;
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
public class QueryRecordServiceTest {

    @Mock
    private QueryRecordStorageRepository queryRecordStorageRepository;

    @InjectMocks
    private QueryRecordService queryRecordService = new QueryRecordServiceImpl();

    @Test
    public void testSaveQueryRecord() {
        QueryRecordStorage record = new QueryRecordStorage();
        // 设置记录属性
        when(queryRecordStorageRepository.save(record)).thenReturn(record);

        QueryRecordStorage savedRecord = queryRecordService.saveQueryRecord(record);
        // 验证属性
        verify(queryRecordStorageRepository, times(1)).save(record);
    }

    @Test
    public void testGetQueryRecordById() {
        QueryRecordStorage record = new QueryRecordStorage();
        record.setRecordId(1);
        // 设置记录属性
        when(queryRecordStorageRepository.findById(1)).thenReturn(Optional.of(record));

        QueryRecordStorage foundRecord = queryRecordService.getQueryRecordById(1);
        assertEquals(1, foundRecord.getRecordId());
        verify(queryRecordStorageRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllQueryRecords() {
        QueryRecordStorage record1 = new QueryRecordStorage();
        // 设置记录 1 属性
        QueryRecordStorage record2 = new QueryRecordStorage();
        // 设置记录 2 属性
        List<QueryRecordStorage> records = Arrays.asList(record1, record2);
        when(queryRecordStorageRepository.findAll()).thenReturn(records);

        List<QueryRecordStorage> allRecords = queryRecordService.getAllQueryRecords();
        assertEquals(2, allRecords.size());
        verify(queryRecordStorageRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteQueryRecord() {
        doNothing().when(queryRecordStorageRepository).deleteById(1);
        queryRecordService.deleteQueryRecord(1);
        verify(queryRecordStorageRepository, times(1)).deleteById(1);
    }
}