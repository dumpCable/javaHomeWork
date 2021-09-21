package com.homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.homework"})
@MapperScan(basePackages="com.homework.mapper")
public class BootrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootrapApplication.class, args);
    }

}
