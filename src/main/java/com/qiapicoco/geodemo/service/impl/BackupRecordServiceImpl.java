package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.BackupRecord;
import com.qiapicoco.geodemo.repository.BackupRecordRepository;
import com.qiapicoco.geodemo.service.BackupRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackupRecordServiceImpl implements BackupRecordService {

    @Autowired
    private BackupRecordRepository backupRecordRepository;

    @Override
    public BackupRecord saveBackupRecord(BackupRecord record) {
        return backupRecordRepository.save(record);
    }

    @Override
    public BackupRecord getBackupRecordById(Integer backupId) {
        return backupRecordRepository.findById(backupId).orElse(null);
    }

    @Override
    public List<BackupRecord> getAllBackupRecords() {
        return backupRecordRepository.findAll();
    }

    @Override
    public void deleteBackupRecord(Integer backupId) {
        backupRecordRepository.deleteById(backupId);
    }
}