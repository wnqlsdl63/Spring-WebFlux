package com.jubin.springboot.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jubin.springboot.reactive.entity.Item;

import reactor.core.publisher.Flux;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
	Flux<Item> findByNameContaining(String partialName);
}
