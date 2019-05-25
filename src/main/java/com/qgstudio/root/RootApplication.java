package com.qgstudio.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author linxu
 * @date 2019/5/23
 */
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@EnableScheduling
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

}
