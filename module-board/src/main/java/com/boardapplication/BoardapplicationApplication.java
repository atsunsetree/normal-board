
package com.boardapplication;

import com.core.entity.EntityBasePackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackageClasses = {EntityBasePackage.class, BoardBasePackage.class})
@EnableJpaAuditing
public class BoardapplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardapplicationApplication.class, args);
    }

}


