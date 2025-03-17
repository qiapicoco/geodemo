package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.SharingRecordDto;
import com.qiapicoco.geodemo.entity.DataSharingRecord;
import com.qiapicoco.geodemo.service.DataSharingService;
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

@WebMvcTest(SharingRecordController.class)
public class SharingRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSharingService dataSharingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveSharingRecord() throws Exception {
        SharingRecordDto sharingRecordDto = new SharingRecordDto();
        // 设置共享记录的相关属性

        DataSharingRecord savedRecord = new DataSharingRecord();
        savedRecord.setRecordId(1);
        // 设置保存后的共享记录的相关属性

        when(dataSharingService.saveSharingRecord(org.mockito.ArgumentMatchers.any(DataSharingRecord.class))).thenReturn(savedRecord);

        mockMvc.perform(post("/api/sharing-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sharingRecordDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.recordId").value(1));
    }

    @Test
    public void testGetSharingRecordById() throws Exception {
        DataSharingRecord record = new DataSharingRecord();
        record.setRecordId(1);
        // 设置共享记录的相关属性

        when(dataSharingService.getSharingRecordById(1)).thenReturn(record);

        mockMvc.perform(get("/api/sharing-records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1));
    }

    @Test
    public void testGetAllSharingRecords() throws Exception {
        DataSharingRecord record1 = new DataSharingRecord();
        record1.setRecordId(1);
        // 设置共享记录 1 的相关属性

        DataSharingRecord record2 = new DataSharingRecord();
        record2.setRecordId(2);
        // 设置共享记录 2 的相关属性

        List<DataSharingRecord> records = Arrays.asList(record1, record2);

        when(dataSharingService.getAllSharingRecords()).thenReturn(records);

        mockMvc.perform(get("/api/sharing-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].recordId").value(1))
                .andExpect(jsonPath("$[1].recordId").value(2));
    }

    @Test
    public void testDeleteSharingRecord() throws Exception {
        mockMvc.perform(delete("/api/sharing-records/1"))
                .andExpect(status().isNoContent());
    }
}