package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.GeographicInformationData;
import com.qiapicoco.geodemo.repository.GeographicInformationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class DataBackupService {

    @Autowired
    private GeographicInformationDataRepository geographicInformationDataRepository;

    @Value("${backup.location:/backup/geodata_backup.csv}")
    private String backupLocation;

    @Scheduled(cron = "0 0 2 * * ?") // 每天凌晨 2 点执行备份
    public void backupData() {
        List<GeographicInformationData> dataList = geographicInformationDataRepository.findAll();
        try (FileWriter writer = new FileWriter(backupLocation)) {
            writer.append("dataId,dataName,dataType,dataDescription,dataFilePath,geographicCoordinateInfo\n");
            for (GeographicInformationData data : dataList) {
                writer.append(data.getDataId() + ",");
                writer.append(data.getDataName() + ",");
                writer.append(data.getDataType() + ",");
                writer.append(data.getDataDescription() + ",");
                writer.append(data.getDataFilePath() + ",");
                writer.append(data.getGeographicCoordinateInfo() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void restoreData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(backupLocation))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(",");
                GeographicInformationData data = new GeographicInformationData();
                data.setDataId(Long.parseLong(values[0]));
                data.setDataName(values[1]);
                data.setDataType(values[2]);
                data.setDataDescription(values[3]);
                data.setDataFilePath(values[4]);
                data.setGeographicCoordinateInfo(values[5]);
                geographicInformationDataRepository.save(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}