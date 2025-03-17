package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class QueryRecordStorageRepositoryTest {

    @Autowired
    private QueryRecordStorageRepository queryRecordStorageRepository;

    @Test
    public void testSaveQueryRecordStorage() {
        QueryRecordStorage record = new QueryRecordStorage();
        // 设置记录属性
        QueryRecordStorage savedRecord = queryRecordStorageRepository.save(record);
        // 验证属性
    }

    @Test
    public void testGetQueryRecordStorageById() {
        QueryRecordStorage record = new QueryRecordStorage();
        // 设置记录属性
        QueryRecordStorage savedRecord = queryRecordStorageRepository.save(record);
        Optional<QueryRecordStorage> foundRecord = queryRecordStorageRepository.findById(savedRecord.getRecordId());
        assertTrue(foundRecord.isPresent());
        // 验证属性
    }
}