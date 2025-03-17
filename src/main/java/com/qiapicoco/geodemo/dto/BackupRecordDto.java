package com.qiapicoco.geodemo.dto;

import java.util.Date;

public class BackupRecordDto {
    private Integer backupId;
    private String backupType;
    private Date backupTime;

    // Getters and Setters
    public Integer getBackupId() {
        return backupId;
    }

    public void setBackupId(Integer backupId) {
        this.backupId = backupId;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }
}