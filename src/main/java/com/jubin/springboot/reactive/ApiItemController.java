package com.jubin.springboot.reactive;

import org.springframework.web.bind.annotation.RestController;

import com.jubin.springboot.reactive.repository.ItemRepository;

@RestController
public class ApiItemController {

	private final ItemRepository itemRepository;

	public ApiItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}


}
