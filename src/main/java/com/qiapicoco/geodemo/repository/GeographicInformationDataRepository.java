package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeographicInformationDataRepository extends JpaRepository<GeographicInformationData, Long> {

    /**
     * 根据数据类型查找地理信息数据
     *
     * @param dataType 数据类型
     * @return 符合条件的地理信息数据列表
     */
    List<GeographicInformationData> findByDataType(String dataType);
}