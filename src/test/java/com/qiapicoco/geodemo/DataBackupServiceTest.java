package com.qiapicoco.geodemo;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.repository.GeographicInformationDataRepository;
import com.qiapicoco.geodemo.service.DataBackupService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataBackupServiceTest {

    @Autowired
    private DataBackupService dataBackupService;

    @MockBean
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @Test
    public void testBackupData() {
        List<GeographicInformationData> dataList = new ArrayList<>();
        GeographicInformationData data = new GeographicInformationData();
        data.setDataId(1L);
        dataList.add(data);

        Mockito.when(geographicInformationDataRepository.findAll()).thenReturn(dataList);

        dataBackupService.backupData();

        File backupFile = new File("backup/geodata_backup.csv");
        assertTrue(backupFile.exists());
    }

    @Test
    public void testRestoreData() {
        dataBackupService.restoreData();
        Mockito.verify(geographicInformationDataRepository, Mockito.atLeastOnce()).save(Mockito.any(GeographicInformationData.class));
    }
}