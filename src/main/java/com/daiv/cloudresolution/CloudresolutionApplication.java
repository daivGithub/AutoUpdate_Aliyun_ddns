package com.daiv.cloudresolution;

import com.daiv.cloudresolution.ymlEntity.ParaMeterEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@PropertySource(value = {"classpath:paraMete.yml", "classpath:redis.yml"})
@EnableConfigurationProperties(ParaMeterEntity.class)
@SpringBootApplication(scanBasePackages = {"com.daiv.cloudresolution"})
@EnableScheduling
public class CloudresolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudresolutionApplication.class, args);
	}
}
