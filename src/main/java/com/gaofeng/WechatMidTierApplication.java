package com.gaofeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.gaofeng.mapper")
public class WechatMidTierApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatMidTierApplication.class, args);
    }
}
