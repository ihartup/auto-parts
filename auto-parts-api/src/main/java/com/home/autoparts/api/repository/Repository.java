package com.home.autoparts.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.home.autoparts.domain.GenericProduct;

public interface Repository<T> {
	
	<S extends T> List<S> save(Iterable<S> entites);
	
	<S extends T> List<S> findAll(Sort sort);

	<S extends T> T insert(S entity);
	
	<S extends T> List<S> findAll();
	
	T getByName(String name);
	
	T getById(String id);
	
	<S extends T> List<S> insert(Iterable<S> entities);
	
	<S extends T> boolean exists(S entity);
	
	<S extends T> void delete(S entity);
	
	<S extends T> T save(S entity);
	
	void deleteAll();
}
