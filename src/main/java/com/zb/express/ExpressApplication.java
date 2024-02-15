package com.zb.express;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@MapperScans({
		@MapperScan(value = "com.zb.express.backend.mapper",nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class),
		@MapperScan(value = "com.zb.express.front.mapper",nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class),
		@MapperScan(value = "com.zb.express.setting.mapper",nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)})
public class ExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpressApplication.class, args);
	}

}
