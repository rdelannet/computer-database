package com.excilys.formation.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.QUser;
import com.excilys.formation.model.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserDAO extends DAO<User>{
	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Override
	public User create(User user) {
			try {
				entityManager.persist(user);
				return user;
			}catch(Exception e) {
				logger.error("Not able to add user",e);
				return null;
			}
	}

	@Override
	public boolean delete(int i) {
		
		QUser quser = QUser.user;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		try {
			queryFactory.delete(quser).where(quser.id.eq(i)).execute();
			return true;
		} catch (Exception e) {
			logger.error("Not able to delete computer",e);
			return false;
		}
	}

	@Override
	public boolean update(User user) {
		
		return false;
	}

	@Override
	public User find(int id) {
		QUser quser = QUser.user;
		JPAQuery<User>  query = new JPAQuery<User> (entityManager);
		try {
			
			return query.from(quser).where(quser.id.eq(id)).fetchOne();
		} catch (Exception e) {
			logger.error("Not able to find the user");
			return null;
		}
	}
	public Optional<User> findUsername(String name) {
		QUser quser = QUser.user;
		JPAQuery<User>  query = new JPAQuery<User> (entityManager);
		try {
			
			return Optional.ofNullable(query.from(quser).where(quser.username.eq(name)).fetchOne());
		} catch (Exception e) {
			logger.error("Not able to find the user");
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		QUser quser = QUser.user;
		JPAQuery<User>  query = new JPAQuery<User> (entityManager);	
		
		try {
			
			return  (ArrayList<User>)  query.from(quser).fetch();
		} catch (Exception e) {
			logger.error("Not able to find all");
			return null;
		}
	}

}
