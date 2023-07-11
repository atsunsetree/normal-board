package com.boardapplication;

import com.core.entity.EntityBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {EntityBasePackage.class, BoardBasePackage.class})
public class BoardapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardapplicationApplication.class, args);
    }

}
