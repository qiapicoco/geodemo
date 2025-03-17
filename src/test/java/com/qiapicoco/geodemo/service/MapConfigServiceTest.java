package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.MapConfiguration;
import com.qiapicoco.geodemo.repository.MapConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MapConfigServiceTest {

    @Mock
    private MapConfigurationRepository mapConfigurationRepository;

    @InjectMocks
    private MapConfigService mapConfigService = new MapConfigServiceImpl();

    @Test
    public void testSaveMapConfig() {
        MapConfiguration config = new MapConfiguration();
        config.setConfigName("testConfig");
        when(mapConfigurationRepository.save(config)).thenReturn(config);

        MapConfiguration savedConfig = mapConfigService.saveMapConfig(config);
        assertEquals("testConfig", savedConfig.getConfigName());
        verify(mapConfigurationRepository, times(1)).save(config);
    }

    @Test
    public void testGetMapConfigById() {
        MapConfiguration config = new MapConfiguration();
        config.setConfigId(1);
        config.setConfigName("testConfig");
        when(mapConfigurationRepository.findById(1)).thenReturn(Optional.of(config));

        MapConfiguration foundConfig = mapConfigService.getMapConfigById(1);
        assertEquals("testConfig", foundConfig.getConfigName());
        verify(mapConfigurationRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllMapConfigs() {
        MapConfiguration config1 = new MapConfiguration();
        config1.setConfigName("config1");
        MapConfiguration config2 = new MapConfiguration();
        config2.setConfigName("config2");
        List<MapConfiguration> configs = Arrays.asList(config1, config2);
        when(mapConfigurationRepository.findAll()).thenReturn(configs);

        List<MapConfiguration> allConfigs = mapConfigService.getAllMapConfigs();
        assertEquals(2, allConfigs.size());
        verify(mapConfigurationRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteMapConfig() {
        doNothing().when(mapConfigurationRepository).deleteById(1);
        mapConfigService.deleteMapConfig(1);
        verify(mapConfigurationRepository, times(1)).deleteById(1);
    }
}