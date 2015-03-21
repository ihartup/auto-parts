package com.home.autoparts.storage.datasource.internalapi;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.home.autoparts.domain.GenericProduct;

public interface MongoProductRepository extends MongoRepository<GenericProduct, String> {
	GenericProduct getByName(String name);
}
