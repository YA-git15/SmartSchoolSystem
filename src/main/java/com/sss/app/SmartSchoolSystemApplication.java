package com.sss.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.sss.app.mapper")
@SpringBootApplication
public class SmartSchoolSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSchoolSystemApplication.class, args);
	}

}
