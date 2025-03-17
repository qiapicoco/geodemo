package com.qiapicoco.geodemo.dto;

public class SharingRecordDto {
    private Integer recordId;
    // 这里可根据 DataSharingRecord 实体类添加具体的共享记录相关字段
    // 例如：共享数据名称、共享时间、共享对象等

    // Getters and Setters
    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}