package org.javaboy.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.vhr.mapper")
public class FinalYearProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalYearProjectApplication.class, args);
    }

}
