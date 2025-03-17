package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.MapConfig;

import java.util.List;

public interface MapConfigService {

    /**
     * 保存地图配置
     * @param config 地图配置实体
     * @return 保存后的配置
     */
    MapConfig saveMapConfig(MapConfig config);

    /**
     * 根据 ID 获取地图配置
     * @param configId 配置 ID
     * @return 对应的配置，如果不存在则返回 null
     */
    MapConfig getMapConfigById(Integer configId);

    /**
     * 获取所有地图配置
     * @return 所有配置的列表
     */
    List<MapConfig> getAllMapConfigs();

    /**
     * 根据 ID 删除地图配置
     * @param configId 配置 ID
     */
    void deleteMapConfig(Integer configId);
}