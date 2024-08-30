package com.example.product.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

// Consumer (read를 하기 때문에)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// converter가 필요 -> kafka가 던져주는 binary code를 json으로 바꿔준다.
	@Bean
	public RecordMessageConverter converter() {
		return new JsonMessageConverter();
	}

}
