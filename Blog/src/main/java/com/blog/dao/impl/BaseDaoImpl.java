package com.blog.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.blog.dao.BaseDao;



@Repository
public class BaseDaoImpl <T> implements BaseDao<T>{
	private Class<T> entityClass;
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>)params[0];
	}
	
	public int findCount(){
		String hql = "select count(*) from " + entityClass.getName();
		System.out.println("count: " + hql);
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) this.hibernateTemplate.find(hql);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;		
	}


	public T findById(int id) {		
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	public void update(T entity) {
		this.hibernateTemplate.update(entity);
	}

	public void save(T entity) {
		this.hibernateTemplate.save(entity);
	}

	public void delete(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	public List<T> findAll() {
		return this.hibernateTemplate.loadAll(entityClass);
	}

	public List<T> findAll(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		// 查询分页数据
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.hibernateTemplate.findByCriteria(criteria,begin,pageSize);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return (List<T>) this.hibernateTemplate.find(hql);
	}	

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... params) {
		return (List<T>) this.hibernateTemplate.find(hql, params);
	}	
}
