package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.BackupRecordDto;
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

        BackupRecord savedRecord = new BackupRecord();
        savedRecord.setBackupId(1);
        savedRecord.setBackupType("Full Backup");
        savedRecord.setBackupTime(new Date());

        when(dataBackupService.saveBackupRecord(org.mockito.ArgumentMatchers.any(BackupRecord.class))).thenReturn(savedRecord);

        mockMvc.perform(post("/api/backup-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(backupRecordDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.backupId").value(1))
                .andExpect(jsonPath("$.backupType").value("Full Backup"));
    }

    @Test
    public void testGetBackupRecordById() throws Exception {
        BackupRecord record = new BackupRecord();
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
        BackupRecord record1 = new BackupRecord();
        record1.setBackupId(1);
        record1.setBackupType("Full Backup");

        BackupRecord record2 = new BackupRecord();
        record2.setBackupId(2);
        record2.setBackupType("Incremental Backup");

        List<BackupRecord> records = Arrays.asList(record1, record2);

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