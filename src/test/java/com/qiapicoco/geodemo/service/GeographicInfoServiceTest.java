package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.repository.GeographicInformationDataRepository;
import com.qiapicoco.geodemo.service.impl.GeographicInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GeographicInfoServiceTest {

    @Mock
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @InjectMocks
    private GeographicInfoService geographicInfoService = new GeographicInfoServiceImpl();

    @Test
    public void testSaveGeographicInfo() {
        GeographicInformationData data = new GeographicInformationData();
        data.setDataName("testData");
        when(geographicInformationDataRepository.save(data)).thenReturn(data);

        GeographicInformationData savedData = geographicInfoService.saveGeographicInfo(data);
        assertEquals("testData", savedData.getDataName());
        verify(geographicInformationDataRepository, times(1)).save(data);
    }

    @Test
    public void testGetGeographicInfoById() {
        GeographicInformationData data = new GeographicInformationData();
        data.setDataId(1);
        data.setDataName("testData");
        when(geographicInformationDataRepository.findById(1)).thenReturn(Optional.of(data));

        GeographicInformationData foundData = geographicInfoService.getGeographicInfoById(1);
        assertEquals("testData", foundData.getDataName());
        verify(geographicInformationDataRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllGeographicInfo() {
        GeographicInformationData data1 = new GeographicInformationData();
        data1.setDataName("data1");
        GeographicInformationData data2 = new GeographicInformationData();
        data2.setDataName("data2");
        List<GeographicInformationData> dataList = Arrays.asList(data1, data2);
        when(geographicInformationDataRepository.findAll()).thenReturn(dataList);

        List<GeographicInformationData> allData = geographicInfoService.getAllGeographicInfo();
        assertEquals(2, allData.size());
        verify(geographicInformationDataRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteGeographicInfo() {
        doNothing().when(geographicInformationDataRepository).deleteById(1);
        geographicInfoService.deleteGeographicInfo(1);
        verify(geographicInformationDataRepository, times(1)).deleteById(1);
    }
}