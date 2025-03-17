package com.qiapicoco.geodemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupUtils {
    private static final Logger logger = LoggerFactory.getLogger(BackupUtils.class);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/geodemo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public static void backupDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String backupFileName = "backup_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".sql";
            Path backupFilePath = Paths.get("backup", backupFileName);
            Files.createDirectories(backupFilePath.getParent());

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "mysqldump",
                    "-u" + DB_USER,
                    "-p" + DB_PASSWORD,
                    "geodemo"
            );
            Process process = processBuilder.start();

            try (OutputStream outputStream = new FileOutputStream(new File(backupFilePath.toString()))) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Database backup successful: {}", backupFilePath);
            } else {
                logger.error("Database backup failed with exit code: {}", exitCode);
            }
        } catch (SQLException | IOException | InterruptedException e) {
            logger.error("Error during database backup", e);
        }
    }
}