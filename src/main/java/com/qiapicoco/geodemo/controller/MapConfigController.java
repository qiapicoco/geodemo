package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.MapConfiguration;
import com.qiapicoco.geodemo.service.MapConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/map-configs")
public class MapConfigController {

    @Autowired
    private MapConfigService mapConfigService;

    @PostMapping
    public ResponseEntity<MapConfiguration> saveMapConfig(@RequestBody MapConfiguration config) {
        MapConfiguration savedConfig = mapConfigService.saveMapConfig(config);
        return new ResponseEntity<>(savedConfig, HttpStatus.CREATED);
    }

    @GetMapping("/{configId}")
    public ResponseEntity<MapConfiguration> getMapConfigById(@PathVariable Integer configId) {
        MapConfiguration config = mapConfigService.getMapConfigById(configId);
        if (config != null) {
            return new ResponseEntity<>(config, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MapConfiguration>> getAllMapConfigs() {
        List<MapConfiguration> configs = mapConfigService.getAllMapConfigs();
        return new ResponseEntity<>(configs, HttpStatus.OK);
    }

    @DeleteMapping("/{configId}")
    public ResponseEntity<Void> deleteMapConfig(@PathVariable Integer configId) {
        mapConfigService.deleteMapConfig(configId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}