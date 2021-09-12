package com.jubin.springboot.reactive.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import com.jubin.springboot.reactive.entity.Item;

@Component
public class TemplateDatabaseLoader {

	@Bean
	CommandLineRunner initialize(MongoOperations mongo) {
		return args -> {
			mongo.save(new Item("Test Item 1", 19.99));
			mongo.save(new Item("Test Item 2", 24.99));
		};
	}
}