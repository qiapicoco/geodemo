package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.DataBackupRecord;
import com.qiapicoco.geodemo.repository.DataBackupRecordRepository;
import com.qiapicoco.geodemo.service.BackupRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackupRecordServiceImpl implements BackupRecordService {

    @Autowired
    private DataBackupRecordRepository dataBackupRecordRepository;

    @Override
    public DataBackupRecord saveBackupRecord(DataBackupRecord record) {
        return dataBackupRecordRepository.save(record);
    }

    @Override
    public DataBackupRecord getBackupRecordById(Integer backupId) {
        return dataBackupRecordRepository.findById(backupId).orElse(null);
    }

    @Override
    public List<DataBackupRecord> getAllBackupRecords() {
        return dataBackupRecordRepository.findAll();
    }

    @Override
    public void deleteBackupRecord(Integer backupId) {
        dataBackupRecordRepository.deleteById(backupId);
    }
}