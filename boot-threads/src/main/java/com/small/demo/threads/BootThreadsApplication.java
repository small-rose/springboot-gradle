package com.small.demo.threads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BootThreadsApplication {

    public static void main(String[] args) {

        SpringApplication.run(BootThreadsApplication.class, args);
        log.info("启动成功！");
    }

}
