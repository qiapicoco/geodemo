package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.MapConfig;
import com.qiapicoco.geodemo.repository.MapConfigRepository;
import com.qiapicoco.geodemo.service.impl.MapConfigServiceImpl;
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
    private MapConfigRepository mapConfigRepository;

    @InjectMocks
    private MapConfigService mapConfigService = new MapConfigServiceImpl();

    @Test
    public void testSaveMapConfig() {
        MapConfig config = new MapConfig();
        config.setConfigName("testConfig");
        when(mapConfigRepository.save(config)).thenReturn(config);

        MapConfig savedConfig = mapConfigService.saveMapConfig(config);
        assertEquals("testConfig", savedConfig.getConfigName());
        verify(mapConfigRepository, times(1)).save(config);
    }

    @Test
    public void testGetMapConfigById() {
        MapConfig config = new MapConfig();
        config.setConfigId(1);
        config.setConfigName("testConfig");
        when(mapConfigRepository.findById(1)).thenReturn(Optional.of(config));

        MapConfig foundConfig = mapConfigService.getMapConfigById(1);
        assertEquals("testConfig", foundConfig.getConfigName());
        verify(mapConfigRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllMapConfigs() {
        MapConfig config1 = new MapConfig();
        config1.setConfigName("config1");
        MapConfig config2 = new MapConfig();
        config2.setConfigName("config2");
        List<MapConfig> configs = Arrays.asList(config1, config2);
        when(mapConfigRepository.findAll()).thenReturn(configs);

        List<MapConfig> allConfigs = mapConfigService.getAllMapConfigs();
        assertEquals(2, allConfigs.size());
        verify(mapConfigRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteMapConfig() {
        doNothing().when(mapConfigRepository).deleteById(1);
        mapConfigService.deleteMapConfig(1);
        verify(mapConfigRepository, times(1)).deleteById(1);
    }
}