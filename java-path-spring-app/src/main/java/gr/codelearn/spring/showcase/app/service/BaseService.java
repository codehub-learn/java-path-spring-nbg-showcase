package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.model.Product;

import java.util.List;

public interface BaseService<T, ID> {
	T create(final T item);

	List<T> createAll(final List<T> items);

	List<T> createAll(T... items);

	void update(T item);

	void delete(T item);

	boolean exists(T item);

	T get(ID id);

	List<T> findAll();

	Long count();
}
