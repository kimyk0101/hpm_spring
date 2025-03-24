package himedia.hpm_spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "himedia.hpm_spring.mappers")
@ComponentScan(basePackages = "himedia.hpm_spring")

public class HpmSpringApplication{

	public static void main(String[] args) {
		SpringApplication.run(HpmSpringApplication.class, args);
	}

}