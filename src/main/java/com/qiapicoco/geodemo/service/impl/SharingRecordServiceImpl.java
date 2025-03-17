package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.SharingRecord;
import com.qiapicoco.geodemo.repository.SharingRecordRepository;
import com.qiapicoco.geodemo.service.SharingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharingRecordServiceImpl implements SharingRecordService {

    @Autowired
    private SharingRecordRepository sharingRecordRepository;

    @Override
    public SharingRecord saveSharingRecord(SharingRecord record) {
        return sharingRecordRepository.save(record);
    }

    @Override
    public SharingRecord getSharingRecordById(Integer recordId) {
        return sharingRecordRepository.findById(recordId).orElse(null);
    }

    @Override
    public List<SharingRecord> getAllSharingRecords() {
        return sharingRecordRepository.findAll();
    }

    @Override
    public void deleteSharingRecord(Integer recordId) {
        sharingRecordRepository.deleteById(recordId);
    }
}