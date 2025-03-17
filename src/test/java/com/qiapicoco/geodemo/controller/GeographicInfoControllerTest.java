package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.GeographicInfoDto;
import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.service.GeographicInfoService;
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

@WebMvcTest(GeographicInfoController.class)
public class GeographicInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeographicInfoService geographicInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveGeographicInfo() throws Exception {
        GeographicInfoDto geographicInfoDto = new GeographicInfoDto();
        geographicInfoDto.setDataName("testData");
        geographicInfoDto.setDataContent("Test content");

        GeographicInformationData savedData = new GeographicInformationData();
        savedData.setDataId(1);
        savedData.setDataName("testData");
        savedData.setDataContent("Test content");

        when(geographicInfoService.saveGeographicInfo(org.mockito.ArgumentMatchers.any(GeographicInformationData.class))).thenReturn(savedData);

        mockMvc.perform(post("/api/geographic-info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(geographicInfoDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dataId").value(1))
                .andExpect(jsonPath("$.dataName").value("testData"));
    }

    @Test
    public void testGetGeographicInfoById() throws Exception {
        GeographicInformationData data = new GeographicInformationData();
        data.setDataId(1);
        data.setDataName("testData");

        when(geographicInfoService.getGeographicInfoById(1)).thenReturn(data);

        mockMvc.perform(get("/api/geographic-info/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dataId").value(1))
                .andExpect(jsonPath("$.dataName").value("testData"));
    }

    @Test
    public void testGetAllGeographicInfo() throws Exception {
        GeographicInformationData data1 = new GeographicInformationData();
        data1.setDataId(1);
        data1.setDataName("data1");

        GeographicInformationData data2 = new GeographicInformationData();
        data2.setDataId(2);
        data2.setDataName("data2");

        List<GeographicInformationData> dataList = Arrays.asList(data1, data2);

        when(geographicInfoService.getAllGeographicInfo()).thenReturn(dataList);

        mockMvc.perform(get("/api/geographic-info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].dataName").value("data1"))
                .andExpect(jsonPath("$[1].dataName").value("data2"));
    }

    @Test
    public void testDeleteGeographicInfo() throws Exception {
        mockMvc.perform(delete("/api/geographic-info/1"))
                .andExpect(status().isNoContent());
    }
}