package com.ufc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ufc.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

}
