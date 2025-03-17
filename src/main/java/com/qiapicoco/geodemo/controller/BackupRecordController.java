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
public class BackupRecordController {

    @Autowired
    private DataBackupService dataBackupService;

    /**
     * 保存数据备份记录
     * @param record 数据备份记录实体
     * @return 保存后的记录及状态码
     */
    @PostMapping
    public ResponseEntity<DataBackupRecord> saveBackupRecord(@RequestBody DataBackupRecord record) {
        DataBackupRecord savedRecord = dataBackupService.saveBackupRecord(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    /**
     * 根据 ID 获取数据备份记录
     * @param recordId 记录 ID
     * @return 对应的记录及状态码
     */
    @GetMapping("/{recordId}")
    public ResponseEntity<DataBackupRecord> getBackupRecordById(@PathVariable Integer recordId) {
        DataBackupRecord record = dataBackupService.getBackupRecordById(recordId);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 获取所有数据备份记录
     * @return 所有记录列表及状态码
     */
    @GetMapping
    public ResponseEntity<List<DataBackupRecord>> getAllBackupRecords() {
        List<DataBackupRecord> records = dataBackupService.getAllBackupRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    /**
     * 根据 ID 删除数据备份记录
     * @param recordId 记录 ID
     * @return 状态码
     */
    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteBackupRecord(@PathVariable Integer recordId) {
        dataBackupService.deleteBackupRecord(recordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}