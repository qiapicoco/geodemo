package com.qiapicoco.geodemo.entity;
import jakarta.persistence.*;
//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "QueryRecordStorageTable")
public class QueryRecordStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(length = 255)
    private String queryCondition;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date queryTime;

    @Column(length = 255)
    private String queryResultStoragePath;

    // Getters and Setters
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public String getQueryResultStoragePath() {
        return queryResultStoragePath;
    }

    public void setQueryResultStoragePath(String queryResultStoragePath) {
        this.queryResultStoragePath = queryResultStoragePath;
    }
}