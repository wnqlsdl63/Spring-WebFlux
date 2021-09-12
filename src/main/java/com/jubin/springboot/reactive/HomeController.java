package com.jubin.springboot.reactive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

import com.jubin.springboot.reactive.entity.Cart;
import com.jubin.springboot.reactive.repository.CartRepository;
import com.jubin.springboot.reactive.repository.ItemRepository;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {

	private ItemRepository itemRepository;
	private CartRepository cartRepository;

	public HomeController(ItemRepository itemRepository,
		CartRepository cartRepository) {
		this.itemRepository = itemRepository;
		this.cartRepository = cartRepository;
	}

	// @GetMapping
	// Mono<String> home() {
	// 	return Mono.just("home");
	// }

	@GetMapping
	Mono<Rendering> home() {
		return Mono.just(Rendering.view("home.html")
			.modelAttribute("items", this.itemRepository.findAll())
			.modelAttribute("cart", this.cartRepository.findById("My Cart")
				.defaultIfEmpty(new Cart("My Cart")))
		.build());
	}

}
