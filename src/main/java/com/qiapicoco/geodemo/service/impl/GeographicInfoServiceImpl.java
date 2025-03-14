package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.repository.GeographicInformationDataRepository;
import com.qiapicoco.geodemo.service.GeographicInfoService;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeographicInfoServiceImpl implements GeographicInfoService {

    @Autowired
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @Override
    public List<GeographicInformationData> getAllGeographicInfo() {
        return geographicInformationDataRepository.findAll();
    }

    @Override
    public List<GeographicInformationData> importData(MultipartFile file) throws IOException {
        File tempFile = convertMultipartFileToFile(file);
        Map<String, Object> params = Map.of("url", tempFile.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(params);
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource featureSource = dataStore.getFeatureSource(typeName);
        FeatureCollection featureCollection = featureSource.getFeatures();

        List<GeographicInformationData> importedData = new ArrayList<>();
        featureCollection.accepts(feature -> {
            GeographicInformationData data = new GeographicInformationData();
            // 假设这里根据实际情况从 feature 中提取数据并设置到 GeographicInformationData 实体
            // 例如：data.setDataName(feature.getAttribute("name").toString());
            importedData.add(data);
        }, null);

        dataStore.dispose();
        tempFile.delete();

        return geographicInformationDataRepository.saveAll(importedData);
    }

    @Override
    public GeographicInformationData updateData(Long dataId, GeographicInformationData updatedData) {
        return geographicInformationDataRepository.findById(dataId)
               .map(data -> {
                    data.setDataName(updatedData.getDataName());
                    data.setDataType(updatedData.getDataType());
                    data.setDataDescription(updatedData.getDataDescription());
                    data.setDataFilePath(updatedData.getDataFilePath());
                    data.setGeographicCoordinateInfo(updatedData.getGeographicCoordinateInfo());
                    return geographicInformationDataRepository.save(data);
                })
               .orElse(null);
    }

    @Override
    public boolean deleteData(Long dataId) {
        if (geographicInformationDataRepository.existsById(dataId)) {
            geographicInformationDataRepository.deleteById(dataId);
            return true;
        }
        return false;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);
        return tempFile;
    }
}
