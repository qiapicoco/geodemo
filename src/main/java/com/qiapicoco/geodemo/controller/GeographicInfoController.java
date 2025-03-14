package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.service.GeographicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/geographic-data")
public class GeographicInfoController {

    @Autowired
    private GeographicInfoService geographicInfoService;

    @GetMapping
    public ResponseEntity<List<GeographicInformationData>> getAllGeographicInfo() {
        List<GeographicInformationData> dataList = geographicInfoService.getAllGeographicInfo();
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<List<GeographicInformationData>> importData(@RequestParam("file") MultipartFile file) {
        try {
            List<GeographicInformationData> importedData = geographicInfoService.importData(file);
            return new ResponseEntity<>(importedData, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{dataId}")
    public ResponseEntity<GeographicInformationData> updateData(@PathVariable Long dataId, @RequestBody GeographicInformationData updatedData) {
        GeographicInformationData data = geographicInfoService.updateData(dataId, updatedData);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{dataId}")
    public ResponseEntity<Void> deleteData(@PathVariable Long dataId) {
        if (geographicInfoService.deleteData(dataId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}