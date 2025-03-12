package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.QueryRecordStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRecordStorageRepository extends JpaRepository<QueryRecordStorage, Long> {

    /**
     * 根据查询条件查找查询记录
     *
     * @param queryCondition 查询条件
     * @return 符合条件的查询记录列表
     */
    List<QueryRecordStorage> findByQueryCondition(String queryCondition);
}