package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.DataSharingRecord;
import com.qiapicoco.geodemo.repository.DataSharingRecordRepository;
import com.qiapicoco.geodemo.service.SharingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharingRecordServiceImpl implements SharingRecordService {

    @Autowired
    private DataSharingRecordRepository dataSharingRecordRepository;

    @Override
    public DataSharingRecord saveSharingRecord(DataSharingRecord record) {
        return dataSharingRecordRepository.save(record);
    }

    @Override
    public DataSharingRecord getSharingRecordById(Integer recordId) {
        return dataSharingRecordRepository.findById(recordId).orElse(null);
    }

    @Override
    public List<DataSharingRecord> getAllSharingRecords() {
        return dataSharingRecordRepository.findAll();
    }

    @Override
    public void deleteSharingRecord(Integer recordId) {
        dataSharingRecordRepository.deleteById(recordId);
    }
}