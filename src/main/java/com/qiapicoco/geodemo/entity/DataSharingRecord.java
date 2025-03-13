package com.qiapicoco.geodemo.entity;

import jakarta.persistence.*;
//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DataSharingRecordTable")
public class DataSharingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sharingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private GeographicInformationData data;

    @Column(length = 255)
    private String sharingObject;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date sharingTime;

    @Column(length = 255)
    private String permissionLevel;

    // Getters and Setters
    public Long getSharingId() {
        return sharingId;
    }

    public void setSharingId(Long sharingId) {
        this.sharingId = sharingId;
    }

    public GeographicInformationData getData() {
        return data;
    }

    public void setData(GeographicInformationData data) {
        this.data = data;
    }

    public String getSharingObject() {
        return sharingObject;
    }

    public void setSharingObject(String sharingObject) {
        this.sharingObject = sharingObject;
    }

    public Date getSharingTime() {
        return sharingTime;
    }

    public void setSharingTime(Date sharingTime) {
        this.sharingTime = sharingTime;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}