package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.MapConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapConfigRepository extends JpaRepository<MapConfig, Long> {
//    public interface MapConfigurationRepository extends JpaRepository<MapConfiguration, Integer> {
    /**
     * 根据地图类型查找地图配置
     *
     * @param mapType 地图类型
     * @return 符合条件的地图配置列表
     */
    List<MapConfig> findByMapType(String mapType);
}