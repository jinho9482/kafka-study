package com.example.product.command;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
	}

}
