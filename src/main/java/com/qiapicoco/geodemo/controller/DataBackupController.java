package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.DataBackupRecord;
import com.qiapicoco.geodemo.service.DataBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backup-records")
public class DataBackupController {

    @Autowired
    private DataBackupService dataBackupService;

    @PostMapping
    public ResponseEntity<DataBackupRecord> saveBackupRecord(@RequestBody DataBackupRecord record) {
        DataBackupRecord savedRecord = dataBackupService.saveBackupRecord(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{backupId}")
    public ResponseEntity<DataBackupRecord> getBackupRecordById(@PathVariable Integer backupId) {
        DataBackupRecord record = dataBackupService.getBackupRecordById(backupId);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DataBackupRecord>> getAllBackupRecords() {
        List<DataBackupRecord> records = dataBackupService.getAllBackupRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @DeleteMapping("/{backupId}")
    public ResponseEntity<Void> deleteBackupRecord(@PathVariable Integer backupId) {
        dataBackupService.deleteBackupRecord(backupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}