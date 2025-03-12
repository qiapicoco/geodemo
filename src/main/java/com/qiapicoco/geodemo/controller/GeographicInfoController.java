package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.service.GeographicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/geographic-info")
public class GeographicInfoController {

    @Autowired
    private GeographicInfoService geographicInfoService;

    @PostMapping("/import")
    public ResponseEntity<List<GeographicInformationData>> importData(@RequestParam("file") MultipartFile file) {
        try {
            List<GeographicInformationData> dataList = geographicInfoService.importData(file);
            return new ResponseEntity<>(dataList, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}