package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.MapConfig;
import com.qiapicoco.geodemo.repository.MapConfigRepository;
import com.qiapicoco.geodemo.service.MapConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapConfigServiceImpl implements MapConfigService {

    @Autowired
    private MapConfigRepository mapConfigRepository;

    @Override
    public MapConfig saveMapConfig(MapConfig config) {
        return mapConfigRepository.save(config);
    }

    @Override
    public MapConfig getMapConfigById(Integer configId) {
        return mapConfigRepository.findById(configId).orElse(null);
    }

    @Override
    public List<MapConfig> getAllMapConfigs() {
        return mapConfigRepository.findAll();
    }

    @Override
    public void deleteMapConfig(Integer configId) {
        mapConfigRepository.deleteById(configId);
    }
}