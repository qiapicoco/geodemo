package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.SharingRecord;
import com.qiapicoco.geodemo.service.DataSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sharing-records")
public class SharingRecordController {

    @Autowired
    private DataSharingService dataSharingService;

    /**
     * 保存数据共享记录
     * @param record 数据共享记录实体
     * @return 保存后的记录及状态码
     */
    @PostMapping
    public ResponseEntity<SharingRecord> saveSharingRecord(@RequestBody SharingRecord record) {
        SharingRecord savedRecord = dataSharingService.saveSharingRecord(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    /**
     * 根据 ID 获取数据共享记录
     * @param recordId 记录 ID
     * @return 对应的记录及状态码
     */
    @GetMapping("/{recordId}")
    public ResponseEntity<SharingRecord> getSharingRecordById(@PathVariable Integer recordId) {
        SharingRecord record = dataSharingService.getSharingRecordById(recordId);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 获取所有数据共享记录
     * @return 所有记录列表及状态码
     */
    @GetMapping
    public ResponseEntity<List<SharingRecord>> getAllSharingRecords() {
        List<SharingRecord> records = dataSharingService.getAllSharingRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    /**
     * 根据 ID 删除数据共享记录
     * @param recordId 记录 ID
     * @return 状态码
     */
    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteSharingRecord(@PathVariable Integer recordId) {
        dataSharingService.deleteSharingRecord(recordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}