package com.home.autoparts.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

public interface Repository<T> {
	
	<S extends T> List<S> save(Iterable<S> entites);
	
	List<T> findAll();

	List<T> findAll(Sort sort);

	<S extends T> S insert(S entity);

	<S extends T> List<S> insert(Iterable<S> entities);
}
