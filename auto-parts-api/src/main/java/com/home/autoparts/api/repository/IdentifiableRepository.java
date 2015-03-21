package com.home.autoparts.api.repository;

public interface IdentifiableRepository<T> {
	<S extends T> S getById(String id);
}
