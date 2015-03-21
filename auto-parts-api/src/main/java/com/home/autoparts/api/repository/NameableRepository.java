package com.home.autoparts.api.repository;

public interface NameableRepository<T> {
	<S extends T> S getByName(String name);
}
