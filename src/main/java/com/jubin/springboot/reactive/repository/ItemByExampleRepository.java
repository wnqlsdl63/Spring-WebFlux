package com.jubin.springboot.reactive.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.jubin.springboot.reactive.entity.Item;

@Repository
public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {
}
