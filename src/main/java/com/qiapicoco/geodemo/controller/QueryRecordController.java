package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import com.qiapicoco.geodemo.service.QueryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/query-records")
public class QueryRecordController {

    @Autowired
    private QueryRecordService queryRecordService;

    /**
     * 保存查询记录
     * @param record 查询记录实体
     * @return 保存后的记录及状态码
     */
    @PostMapping
    public ResponseEntity<QueryRecordStorage> saveQueryRecord(@RequestBody QueryRecordStorage record) {
        QueryRecordStorage savedRecord = queryRecordService.saveQueryRecord(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    /**
     * 根据 ID 获取查询记录
     * @param recordId 记录 ID
     * @return 对应的记录及状态码
     */
    @GetMapping("/{recordId}")
    public ResponseEntity<QueryRecordStorage> getQueryRecordById(@PathVariable Integer recordId) {
        QueryRecordStorage record = queryRecordService.getQueryRecordById(recordId);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 获取所有查询记录
     * @return 所有记录列表及状态码
     */
    @GetMapping
    public ResponseEntity<List<QueryRecordStorage>> getAllQueryRecords() {
        List<QueryRecordStorage> records = queryRecordService.getAllQueryRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    /**
     * 根据 ID 删除查询记录
     * @param recordId 记录 ID
     * @return 状态码
     */
    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteQueryRecord(@PathVariable Integer recordId) {
        queryRecordService.deleteQueryRecord(recordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}