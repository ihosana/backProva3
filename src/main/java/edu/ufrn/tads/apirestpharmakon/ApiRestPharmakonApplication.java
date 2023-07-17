package edu.ufrn.tads.apirestpharmakon;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
//@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ApiRestPharmakonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestPharmakonApplication.class, args);
	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

}
