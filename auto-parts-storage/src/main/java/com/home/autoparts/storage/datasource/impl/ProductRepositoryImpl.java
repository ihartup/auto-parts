package com.home.autoparts.storage.datasource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.home.autoparts.api.repository.ProductRepository;
import com.home.autoparts.domain.GenericProduct;
import com.home.autoparts.storage.datasource.internalapi.MongoProductRepository;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private MongoProductRepository mongoProductRepository;
	
	@Override
	public <S extends GenericProduct> List<S> save(Iterable<S> entites) {
		return mongoProductRepository.save(entites);
	}

	@Override
	public List<GenericProduct> findAll() {
		return mongoProductRepository.findAll();
	}

	@Override
	public List<GenericProduct> findAll(Sort sort) {
		return mongoProductRepository.findAll(sort);
	}

	@Override
	public <S extends GenericProduct> S insert(S entity) {
		return mongoProductRepository.insert(entity);
	}

	@Override
	public <S extends GenericProduct> List<S> insert(Iterable<S> entities) {
		return mongoProductRepository.insert(entities);
	}

	@Override
	public <S extends GenericProduct> S getByName(String name) {
		return (S)mongoProductRepository.getByName(name);
	}

}
