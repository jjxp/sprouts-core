package org.sprouts.backend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.sprouts.backend.util.PopulateDatabase;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class BackEndServer implements ApplicationRunner {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "backend-server");
		SpringApplication.run(BackEndServer.class, args);
	}

	public void run(ApplicationArguments arg0) throws Exception {
		PopulateDatabase.main(null);
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)          
          .select()
          .apis(RequestHandlerSelectors.basePackage("org.sprouts.backend.controller"))
          .build();
    }

}