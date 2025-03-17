package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.QueryRecordDto;
import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import com.qiapicoco.geodemo.service.QueryRecordService;
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

@WebMvcTest(QueryRecordController.class)
public class QueryRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryRecordService queryRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveQueryRecord() throws Exception {
        QueryRecordDto queryRecordDto = new QueryRecordDto();
        // 设置查询记录的相关属性

        QueryRecordStorage savedRecord = new QueryRecordStorage();
        savedRecord.setRecordId(1);
        // 设置保存后的查询记录的相关属性

        when(queryRecordService.saveQueryRecord(org.mockito.ArgumentMatchers.any(QueryRecordStorage.class))).thenReturn(savedRecord);

        mockMvc.perform(post("/api/query-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(queryRecordDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.recordId").value(1));
    }

    @Test
    public void testGetQueryRecordById() throws Exception {
        QueryRecordStorage record = new QueryRecordStorage();
        record.setRecordId(1);
        // 设置查询记录的相关属性

        when(queryRecordService.getQueryRecordById(1)).thenReturn(record);

        mockMvc.perform(get("/api/query-records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(1));
    }

    @Test
    public void testGetAllQueryRecords() throws Exception {
        QueryRecordStorage record1 = new QueryRecordStorage();
        record1.setRecordId(1);
        // 设置查询记录 1 的相关属性

        QueryRecordStorage record2 = new QueryRecordStorage();
        record2.setRecordId(2);
        // 设置查询记录 2 的相关属性

        List<QueryRecordStorage> records = Arrays.asList(record1, record2);

        when(queryRecordService.getAllQueryRecords()).thenReturn(records);

        mockMvc.perform(get("/api/query-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].recordId").value(1))
                .andExpect(jsonPath("$[1].recordId").value(2));
    }

    @Test
    public void testDeleteQueryRecord() throws Exception {
        mockMvc.perform(delete("/api/query-records/1"))
                .andExpect(status().isNoContent());
    }
}