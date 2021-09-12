package com.jubin.springboot.reactive.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jubin.springboot.reactive.entity.Item;

@Repository
public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
