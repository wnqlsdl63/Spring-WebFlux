package com.jubin.springboot.reactive.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.jubin.springboot.reactive.entity.Cart;
import com.jubin.springboot.reactive.entity.CartItem;
import com.jubin.springboot.reactive.entity.Item;
import com.jubin.springboot.reactive.repository.CartRepository;
import com.jubin.springboot.reactive.repository.ItemByExampleRepository;
import com.jubin.springboot.reactive.repository.ItemRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartService {
	private ItemRepository itemRepository;
	private ItemByExampleRepository exampleRepository;
	private CartRepository cartRepository;

	public CartService(ItemRepository itemRepository,
		ItemByExampleRepository exampleRepository, CartRepository cartRepository) {
		this.itemRepository = itemRepository;
		this.exampleRepository = exampleRepository;
		this.cartRepository = cartRepository;
	}

	public Mono<Cart> addToCart(String cartId, String id) {
		return this.cartRepository.findById(cartId) // <3>
			.defaultIfEmpty(new Cart(cartId)) //
			.flatMap(cart -> cart.getCartItems().stream() // <4>
				.filter(cartItem -> cartItem.getItem() //
					.getId().equals(id)) //
				.findAny() //
				.map(cartItem -> {
					cartItem.increment();
					return Mono.just(cart);
				}).orElseGet(() -> {
					return this.itemRepository.findById(id) //
						.map(CartItem::new) // <4>
						.doOnNext(cartItem -> cart.getCartItems().add(cartItem)) //
						.map(cartItem -> cart);
				}))
			.flatMap(this.cartRepository::save); // <6>
	}

	public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
		Item item = new Item(name, description, 0.0);

		ExampleMatcher matcher = (useAnd ? ExampleMatcher.matchingAll() : ExampleMatcher.matchingAny())
			.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // like searching
			.withIgnoreCase()
			.withIgnorePaths("price");	// double is not null, ignore 'price' field

		Example<Item> probe = Example.of(item, matcher);

		return exampleRepository.findAll(probe); // query excute
	}
}
