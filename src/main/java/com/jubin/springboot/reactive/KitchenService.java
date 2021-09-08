package com.jubin.springboot.reactive;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class KitchenService {

	private Random picker = new Random();

	Flux<Dish> getDishes() {
		return Flux.<Dish>generate(sink -> sink.next(randomDish()))
			.delayElements(Duration.ofMillis(250));
	}

	private Dish randomDish() {
		return menu.get(picker.nextInt(menu.size()));
	}
	private List<Dish> menu = Arrays.asList(
		new Dish("First Dish menu"),
		new Dish("Second Dish menu"),
		new Dish("Third Dish Menu")
	);


}
