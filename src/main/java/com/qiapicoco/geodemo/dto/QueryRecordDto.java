package com.qiapicoco.geodemo.dto;

public class QueryRecordDto {
    private Integer recordId;
    // 这里可根据 QueryRecordStorage 实体类添加具体的查询记录相关字段
    // 例如：查询内容、查询时间等

    // Getters and Setters
    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}