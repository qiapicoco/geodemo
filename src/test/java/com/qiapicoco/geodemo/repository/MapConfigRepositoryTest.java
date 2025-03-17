package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.MapConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MapConfigRepositoryTest {

    @Autowired
    private MapConfigRepository mapConfigRepository;

    @Test
    public void testSaveMapConfiguration() {
        MapConfig config = new MapConfig();
        config.setConfigName("testConfig");
        MapConfig savedConfig = mapConfigRepository.save(config);
        assertEquals("testConfig", savedConfig.getConfigName());
    }

    @Test
    public void testGetMapConfigurationById() {
        MapConfig config = new MapConfig();
        config.setConfigName("testConfig");
        MapConfig savedConfig = mapConfigRepository.save(config);
        Optional<MapConfig> foundConfig = mapConfigRepository.findById(savedConfig.getConfigId());
        assertTrue(foundConfig.isPresent());
        assertEquals("testConfig", foundConfig.get().getConfigName());
    }
}