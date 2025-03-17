package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import com.qiapicoco.geodemo.repository.QueryRecordStorageRepository;
import com.qiapicoco.geodemo.service.QueryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryRecordServiceImpl implements QueryRecordService {

    @Autowired
    private QueryRecordStorageRepository queryRecordStorageRepository;

    @Override
    public QueryRecordStorage saveQueryRecord(QueryRecordStorage record) {
        return queryRecordStorageRepository.save(record);
    }

    @Override
    public QueryRecordStorage getQueryRecordById(Integer recordId) {
        return queryRecordStorageRepository.findById(recordId).orElse(null);
    }

    @Override
    public List<QueryRecordStorage> getAllQueryRecords() {
        return queryRecordStorageRepository.findAll();
    }

    @Override
    public void deleteQueryRecord(Integer recordId) {
        queryRecordStorageRepository.deleteById(recordId);
    }
}