package com.springcloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootServicioEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioEurekaserverApplication.class, args);
	}

}
