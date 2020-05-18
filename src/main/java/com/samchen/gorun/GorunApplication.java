package com.samchen.gorun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.samchen.gorun.mapper")//使用MapperScan批量扫描所有的Mapper接口；
public class GorunApplication {

	public static void main(String[] args) {
		SpringApplication.run(GorunApplication.class, args);
	}

}
