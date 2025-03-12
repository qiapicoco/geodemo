package com.qiapicoco.geodemo;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.repository.GeographicInformationDataRepository;
import com.qiapicoco.geodemo.service.GeographicInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeographicInfoServiceTest {

    @Autowired
    private GeographicInfoService geographicInfoService;

    @MockBean
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @Test
    public void testImportData() throws IOException {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        List<GeographicInformationData> mockImportedData = new ArrayList<>();
        GeographicInformationData data = new GeographicInformationData();
        data.setDataId(1L);
        mockImportedData.add(data);

        Mockito.when(geographicInformationDataRepository.saveAll(Mockito.anyList())).thenReturn(mockImportedData);

        List<GeographicInformationData> result = geographicInfoService.importData(mockFile);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateData() {
        Long dataId = 1L;
        GeographicInformationData updatedData = new GeographicInformationData();
        updatedData.setDataId(dataId);
        updatedData.setDataName("Updated Name");

        GeographicInformationData existingData = new GeographicInformationData();
        existingData.setDataId(dataId);
        existingData.setDataName("Old Name");

        Mockito.when(geographicInformationDataRepository.findById(dataId)).thenReturn(Optional.of(existingData));
        Mockito.when(geographicInformationDataRepository.save(Mockito.any(GeographicInformationData.class))).thenReturn(updatedData);

        GeographicInformationData result = geographicInfoService.updateData(dataId, updatedData);

        assertNotNull(result);
        assertEquals("Updated Name", result.getDataName());
    }

    @Test
    public void testDeleteData() {
        Long dataId = 1L;
        Mockito.when(geographicInformationDataRepository.existsById(dataId)).thenReturn(true);

        boolean result = geographicInfoService.deleteData(dataId);

        assertTrue(result);
        Mockito.verify(geographicInformationDataRepository, Mockito.times(1)).deleteById(dataId);
    }
}