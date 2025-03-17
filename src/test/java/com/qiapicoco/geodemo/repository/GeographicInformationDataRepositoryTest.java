package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GeographicInformationDataRepositoryTest {

    @Autowired
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @Test
    public void testSaveGeographicInformationData() {
        GeographicInformationData data = new GeographicInformationData();
        data.setDataName("testData");
        GeographicInformationData savedData = geographicInformationDataRepository.save(data);
        assertEquals("testData", savedData.getDataName());
    }

    @Test
    public void testGetGeographicInformationDataById() {
        GeographicInformationData data = new GeographicInformationData();
        data.setDataName("testData");
        GeographicInformationData savedData = geographicInformationDataRepository.save(data);
        Optional<GeographicInformationData> foundData = geographicInformationDataRepository.findById(savedData.getDataId());
        assertTrue(foundData.isPresent());
        assertEquals("testData", foundData.get().getDataName());
    }
}