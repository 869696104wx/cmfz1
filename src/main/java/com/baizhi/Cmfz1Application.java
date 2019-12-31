package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.baizhi.dao")
@org.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
@SpringBootApplication
public class Cmfz1Application {
    public static void main(String[] args) {
        SpringApplication.run(Cmfz1Application.class, args);
    }

    public void m1() {
    }
}
