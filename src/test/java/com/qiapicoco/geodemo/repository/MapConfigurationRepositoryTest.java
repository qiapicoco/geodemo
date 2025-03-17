package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.MapConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MapConfigurationRepositoryTest {

    @Autowired
    private MapConfigurationRepository mapConfigurationRepository;

    @Test
    public void testSaveMapConfiguration() {
        MapConfiguration config = new MapConfiguration();
        config.setConfigName("testConfig");
        MapConfiguration savedConfig = mapConfigurationRepository.save(config);
        assertEquals("testConfig", savedConfig.getConfigName());
    }

    @Test
    public void testGetMapConfigurationById() {
        MapConfiguration config = new MapConfiguration();
        config.setConfigName("testConfig");
        MapConfiguration savedConfig = mapConfigurationRepository.save(config);
        Optional<MapConfiguration> foundConfig = mapConfigurationRepository.findById(savedConfig.getConfigId());
        assertTrue(foundConfig.isPresent());
        assertEquals("testConfig", foundConfig.get().getConfigName());
    }
}