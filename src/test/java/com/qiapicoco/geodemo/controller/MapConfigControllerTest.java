package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.MapConfigDto;
import com.qiapicoco.geodemo.entity.MapConfiguration;
import com.qiapicoco.geodemo.service.MapConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MapConfigController.class)
public class MapConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapConfigService mapConfigService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveMapConfig() throws Exception {
        MapConfigDto mapConfigDto = new MapConfigDto();
        mapConfigDto.setConfigName("testConfig");
        mapConfigDto.setConfigValue("Test value");

        MapConfiguration savedConfig = new MapConfiguration();
        savedConfig.setConfigId(1);
        savedConfig.setConfigName("testConfig");
        savedConfig.setConfigValue("Test value");

        when(mapConfigService.saveMapConfig(org.mockito.ArgumentMatchers.any(MapConfiguration.class))).thenReturn(savedConfig);

        mockMvc.perform(post("/api/map-configs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mapConfigDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.configId").value(1))
                .andExpect(jsonPath("$.configName").value("testConfig"));
    }

    @Test
    public void testGetMapConfigById() throws Exception {
        MapConfiguration config = new MapConfiguration();
        config.setConfigId(1);
        config.setConfigName("testConfig");

        when(mapConfigService.getMapConfigById(1)).thenReturn(config);

        mockMvc.perform(get("/api/map-configs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.configId").value(1))
                .andExpect(jsonPath("$.configName").value("testConfig"));
    }

    @Test
    public void testGetAllMapConfigs() throws Exception {
        MapConfiguration config1 = new MapConfiguration();
        config1.setConfigId(1);
        config1.setConfigName("config1");

        MapConfiguration config2 = new MapConfiguration();
        config2.setConfigId(2);
        config2.setConfigName("config2");

        List<MapConfiguration> configs = Arrays.asList(config1, config2);

        when(mapConfigService.getAllMapConfigs()).thenReturn(configs);

        mockMvc.perform(get("/api/map-configs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].configName").value("config1"))
                .andExpect(jsonPath("$[1].configName").value("config2"));
    }

    @Test
    public void testDeleteMapConfig() throws Exception {
        mockMvc.perform(delete("/api/map-configs/1"))
                .andExpect(status().isNoContent());
    }
}