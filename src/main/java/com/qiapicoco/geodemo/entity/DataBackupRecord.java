package com.qiapicoco.geodemo.entity;

import jakarta.persistence.*;
//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DataBackupRecordTable")
public class DataBackupRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long backupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private GeographicInformationData data;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date backupTime;

    @Column(length = 255)
    private String backupPath;

    // Getters and Setters
    public Long getBackupId() {
        return backupId;
    }

    public void setBackupId(Long backupId) {
        this.backupId = backupId;
    }

    public GeographicInformationData getData() {
        return data;
    }

    public void setData(GeographicInformationData data) {
        this.data = data;
    }

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }
}