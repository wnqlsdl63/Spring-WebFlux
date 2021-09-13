package com.jubin.springboot.reactive;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jubin.springboot.reactive.entity.Item;

public class ItemUnitTest {

	@Test
	void itemBasicShouldWork() {
		Item item = new Item("Name 1", "Description 1", 21.12);

		assertThat(item.getName()).isEqualTo("Name 1");
	}
}
