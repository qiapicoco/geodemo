package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.BackupRecordDto;
import com.qiapicoco.geodemo.entity.DataBackupRecord;
import com.qiapicoco.geodemo.service.DataBackupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BackupRecordController.class)
public class BackupRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataBackupService dataBackupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveBackupRecord() throws Exception {
        BackupRecordDto backupRecordDto = new BackupRecordDto();
        backupRecordDto.setBackupType("Full Backup");
        backupRecordDto.setBackupTime(new Date());

        DataBackupRecord savedRecord = new DataBackupRecord();
        savedRecord.setBackupId(1);
        savedRecord.setBackupType("Full Backup");
        savedRecord.setBackupTime(new Date());

        when(dataBackupService.saveBackupRecord(org.mockito.ArgumentMatchers.any(DataBackupRecord.class))).thenReturn(savedRecord);

        mockMvc.perform(post("/api/backup-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(backupRecordDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.backupId").value(1))
                .andExpect(jsonPath("$.backupType").value("Full Backup"));
    }

    @Test
    public void testGetBackupRecordById() throws Exception {
        DataBackupRecord record = new DataBackupRecord();
        record.setBackupId(1);
        record.setBackupType("Full Backup");

        when(dataBackupService.getBackupRecordById(1)).thenReturn(record);

        mockMvc.perform(get("/api/backup-records/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.backupId").value(1))
                .andExpect(jsonPath("$.backupType").value("Full Backup"));
    }

    @Test
    public void testGetAllBackupRecords() throws Exception {
        DataBackupRecord record1 = new DataBackupRecord();
        record1.setBackupId(1);
        record1.setBackupType("Full Backup");

        DataBackupRecord record2 = new DataBackupRecord();
        record2.setBackupId(2);
        record2.setBackupType("Incremental Backup");

        List<DataBackupRecord> records = Arrays.asList(record1, record2);

        when(dataBackupService.getAllBackupRecords()).thenReturn(records);

        mockMvc.perform(get("/api/backup-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].backupType").value("Full Backup"))
                .andExpect(jsonPath("$[1].backupType").value("Incremental Backup"));
    }

    @Test
    public void testDeleteBackupRecord() throws Exception {
        mockMvc.perform(delete("/api/backup-records/1"))
                .andExpect(status().isNoContent());
    }
}