package com.home.autoparts.storage.datasource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.autoparts.api.repository.ProductRepository;
import com.home.autoparts.domain.GenericProduct;
import com.home.autoparts.storage.datasource.domain.MongoProduct;
import com.home.autoparts.storage.datasource.repo.MongoProductRepository;

@SuppressWarnings("unchecked")
@Service
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private MongoProductRepository mongoProductRepository;

	@Override
	public MongoProduct getByName(String name) {
		return mongoProductRepository.getByName(name);
	}

	@Override
	public <S extends GenericProduct> GenericProduct insert(S entity) {
		return mongoProductRepository.insert((MongoProduct)entity);
	}

	@Override
	public <S extends GenericProduct> List<S> findAll() {
		return (List<S>)mongoProductRepository.findAll();
	}

	@Override
	public <S extends GenericProduct> List<S> save(Iterable<S> entities) {
		return (List<S>)mongoProductRepository.save((Iterable<MongoProduct>)entities);
	}

	@Override
	public <S extends GenericProduct> List<S> findAll(Sort sort) {
		return (List<S>)mongoProductRepository.findAll(sort);
	}

	@Override
	public GenericProduct getById(String id) {
		return mongoProductRepository.getById(id);
	}

	@Override
	public <S extends GenericProduct> List<S> insert(Iterable<S> entities) {
		return (List<S>)mongoProductRepository.insert((Iterable<MongoProduct>)entities);
	}

	@Override
	public <S extends GenericProduct> boolean exists(S entity) {
		return mongoProductRepository.exists(entity.getId());
	}

	@Override
	public <S extends GenericProduct> void delete(S entity) {
		mongoProductRepository.delete((MongoProduct)entity);
	}
	
	@Override
	public <S extends GenericProduct> GenericProduct save(S entity) {
		return mongoProductRepository.save((MongoProduct)entity);
	}
	
	@Override
	public void deleteAll() {
		mongoProductRepository.deleteAll();
	}
}
