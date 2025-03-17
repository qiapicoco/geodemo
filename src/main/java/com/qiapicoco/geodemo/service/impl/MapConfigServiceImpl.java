package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.MapConfiguration;
import com.qiapicoco.geodemo.repository.MapConfigurationRepository;
import com.qiapicoco.geodemo.service.MapConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapConfigServiceImpl implements MapConfigService {

    @Autowired
    private MapConfigurationRepository mapConfigurationRepository;

    @Override
    public MapConfiguration saveMapConfig(MapConfiguration config) {
        return mapConfigurationRepository.save(config);
    }

    @Override
    public MapConfiguration getMapConfigById(Integer configId) {
        return mapConfigurationRepository.findById(configId).orElse(null);
    }

    @Override
    public List<MapConfiguration> getAllMapConfigs() {
        return mapConfigurationRepository.findAll();
    }

    @Override
    public void deleteMapConfig(Integer configId) {
        mapConfigurationRepository.deleteById(configId);
    }
}