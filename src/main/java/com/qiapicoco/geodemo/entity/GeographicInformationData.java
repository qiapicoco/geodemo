package com.qiapicoco.geodemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
//import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "GeographicInformationDataTable")
public class GeographicInformationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataId;

    @Size(max = 255, message = "数据名称长度不能超过255个字符")
    private String dataName;

    @Size(max = 255, message = "数据类型长度不能超过255个字符")
    private String dataType;

    @Size(max = 255, message = "数据描述长度不能超过255个字符")
    private String dataDescription;

    @Size(max = 255, message = "数据文件路径长度不能超过255个字符")
    private String dataFilePath;

    @Column(columnDefinition = "GEOMETRY")
    private Object geographicCoordinateInfo;

    // Getters and Setters
    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public void setDataDescription(String dataDescription) {
        this.dataDescription = dataDescription;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    public Object getGeographicCoordinateInfo() {
        return geographicCoordinateInfo;
    }

    public void setGeographicCoordinateInfo(Object geographicCoordinateInfo) {
        this.geographicCoordinateInfo = geographicCoordinateInfo;
    }
}
