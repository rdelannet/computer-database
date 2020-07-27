package com.excilys.formation.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


@Repository
public  abstract class DAO<T> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public DAO() {
		
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(int i);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract List<T> findAll();
}
