package com.qiapicoco.geodemo.entity;

import jakarta.persistence.*;

//import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MapConfigurationTable")
public class MapConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configId;

    @Size(max = 255)
    private String mapType;

    @Size(max = 255)
    private String layerInfo;

    // Getters and Setters
    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getLayerInfo() {
        return layerInfo;
    }

    public void setLayerInfo(String layerInfo) {
        this.layerInfo = layerInfo;
    }
}