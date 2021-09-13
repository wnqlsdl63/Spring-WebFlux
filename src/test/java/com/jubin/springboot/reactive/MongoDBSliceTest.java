package com.jubin.springboot.reactive;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.jubin.springboot.reactive.entity.Item;
import com.jubin.springboot.reactive.repository.ItemRepository;

import reactor.test.StepVerifier;

@DataMongoTest
public class MongoDBSliceTest {
	@Autowired
	ItemRepository itemRepository;

	@Test
	void saveItems() {
		Item saveItem = new Item("Name1", "Description 1", 21.12);

		itemRepository.save(saveItem)
			.as(StepVerifier::create)
			.expectNextMatches(item -> {
				assertThat(item.getId()).isNotNull();
				assertThat(item.getName()).isEqualTo("Name1");
				assertThat(item.getDescription()).isEqualTo("Description 1");

				return true;
			}).verifyComplete();

	}
}
