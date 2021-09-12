package com.jubin.springboot.reactive.service;

import org.springframework.stereotype.Service;

import com.jubin.springboot.reactive.entity.Cart;
import com.jubin.springboot.reactive.entity.CartItem;
import com.jubin.springboot.reactive.repository.CartRepository;
import com.jubin.springboot.reactive.repository.ItemRepository;

import reactor.core.publisher.Mono;

@Service
public class CartService {
	private ItemRepository itemRepository;
	private CartRepository cartRepository;

	public CartService(ItemRepository itemRepository,
		CartRepository cartRepository) {
		this.itemRepository = itemRepository;
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
}
