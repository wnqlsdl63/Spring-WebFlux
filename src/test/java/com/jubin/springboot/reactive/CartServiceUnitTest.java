package com.jubin.springboot.reactive;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jubin.springboot.reactive.entity.Cart;
import com.jubin.springboot.reactive.entity.CartItem;
import com.jubin.springboot.reactive.entity.Item;
import com.jubin.springboot.reactive.repository.CartRepository;
import com.jubin.springboot.reactive.repository.ItemRepository;
import com.jubin.springboot.reactive.service.CartService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class CartServiceUnitTest {
	CartService cartService;
	@MockBean
	ItemRepository itemRepository;
	@MockBean
	CartRepository cartRepository;

	@BeforeEach
	void setUp() {
		Item item = new Item("Name1", "Description 1", 21.12);
		CartItem cartItem = new CartItem(item);
		Cart cart = new Cart("My Cart", Collections.singletonList(cartItem));

		when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
		when(itemRepository.findById(anyString())).thenReturn(Mono.just(item));
		when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(cart));

		cartService = new CartService(itemRepository, cartRepository);
	}

	@Test
	void addItemToEmptyCartShouldProduceOneCartItem() {

		cartService.addToCart("My cart", "Name1")
			.as(StepVerifier::create) 		// StepVerifier : subscribe roll
			.expectNextMatches(cart -> {
				assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
					.containsExactlyInAnyOrder(1);

				assertThat(cart.getCartItems()).extracting(CartItem::getItem)
					.containsExactly(new Item("Name1", "Description 1", 21.12));

				return true;
			}).verifyComplete();	// verifyComplete : confirm onComplete signal
	}

	@Test
	void addItemToEmptyCartShouldProduceOneCartItem2() {

	}
}
