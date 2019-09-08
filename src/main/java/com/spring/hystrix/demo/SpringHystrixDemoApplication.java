package com.spring.hystrix.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
public class SpringHystrixDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHystrixDemoApplication.class, args);
	}

}
