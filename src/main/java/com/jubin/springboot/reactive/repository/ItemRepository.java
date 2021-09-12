package com.jubin.springboot.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jubin.springboot.reactive.entity.Item;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
