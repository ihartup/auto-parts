package com.home.autoparts.storage.datasource.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.home.autoparts.storage.datasource.domain.MongoProduct;

public interface MongoProductRepository extends MongoRepository<MongoProduct, String> {
	MongoProduct getByName(String name);
	MongoProduct getById(String id);
}
