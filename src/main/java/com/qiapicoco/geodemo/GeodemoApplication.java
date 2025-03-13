package com.qiapicoco.geodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.qiapicoco.geodemo"}) // 明确指定扫描路径
@EnableScheduling
public class GeodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeodemoApplication.class, args);
    }

}
