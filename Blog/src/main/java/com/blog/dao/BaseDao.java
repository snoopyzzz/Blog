package com.blog.dao;

import java.util.List;

public interface BaseDao<T> {
	int findCount();

	List<T> findAll();
	
	List<T> findAll(int begin, int pageSize);

	T findById(int id);

	void update(T t);

	void delete(T t);

	void save(T t);
	
	List<T> find(String hql);
	
	List<T> find(String hql, Object... params);
}
