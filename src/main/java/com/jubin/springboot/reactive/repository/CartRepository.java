package com.jubin.springboot.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jubin.springboot.reactive.entity.Cart;

public interface CartRepository extends ReactiveCrudRepository<Cart,String> {
}
