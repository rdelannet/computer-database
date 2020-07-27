package com.excilys.formation.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.model.Computer;
import com.excilys.formation.model.QCompany;
import com.excilys.formation.model.QComputer;
import com.excilys.formation.pagination.Page;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;



@Repository
public class ComputerDAO extends DAO<Computer>{

	private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	
	
	public ComputerDAO() {
		
				
	}
	@Transactional
	public boolean create(Computer computer)  {
		
		
		try {
			entityManager.persist(computer);
			return true;

		}catch (Exception dae) {
			logger.error("Not able to add computer",dae);
			return false;
		}	
	}
	
	@Transactional
	public boolean delete(int id) {
		
		/*try {
			MapSqlParameterSource vParams = new MapSqlParameterSource();
			 vParams.addValue("id",id);
			 NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(connect.getHikariDataSource());
			 vJdbcTemplate.update(delete,vParams);
	
		}catch(Exception eSQL) {
			logger.error("Error Delete Computer");
			eSQL.printStackTrace();
			return false;
		}
		return true;*/
		QComputer qcomputer = QComputer.computer;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		try {
			queryFactory.delete(qcomputer).where(qcomputer.id.eq(id)).execute();
			return true;
		} catch (Exception e) {
			logger.error("Not able to delete computer",e);
			return false;
		}
		
		
	}
	@Transactional
	public boolean update(Computer computer) {
		
		
		QComputer qcomputer = QComputer.computer;
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

		try {
			queryFactory.update(qcomputer)
			.where(qcomputer.id.eq(computer.getId()))
			.set(qcomputer.name, computer.getName())
			.set(qcomputer.introduced, computer.getDateInt())
			.set(qcomputer.discontinued, computer.getDateDisc())
			.set(qcomputer.company.id, computer.getCompany().getId())
			.execute();
			return true;

		}catch (Exception e) {
			logger.error("Not able to update computer",e);
			return false;
		}	
	}
	

	public Computer find(int id) {
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery<Computer>  query = new JPAQuery<Computer> (entityManager);
		try {
				
			return query.from(computer).leftJoin(company).on(computer.company.id.eq(company.id)).where(computer.id.eq(id)).fetchOne();
		} catch (Exception e) {
			logger.error("Not able to find");
			return null;
		}
		
		/*MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("id", id);
		
		Computer computer = jdbcTemplate.queryForObject(findComputer, vParams,new MapperRowComputer());
		return computer;*/
	}

	@Override
	public List<Computer> findAll() {
		QComputer computer = QComputer.computer;
		JPAQuery<Computer>  query = new JPAQuery<Computer> (entityManager);	
		/*List<Computer> vListStatut = null;
		vListStatut = jdbcTemplate.query(findAllComputer, new MapperRowComputer());*/
		try {
			
			return  (ArrayList<Computer>)  query.from(computer).fetch();
		} catch (Exception e) {
			logger.error("Not able to find all");
			return null;
		}
		
	
        
	}
	
	public Long findMaxElement() {
		
		QComputer computer = QComputer.computer;
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		try {
			return query.from(computer).fetchCount();
		} catch (Exception e) {
			logger.error("Not able to find max elem");
			return 0L;
		}
		/*JdbcTemplate vJdbcTemplate = new JdbcTemplate(connect.getHikariDataSource());
		int maxElem = vJdbcTemplate.queryForObject(count, Integer.class);
		
		return maxElem;*/
	}
	
	public List<Computer> findAllPages(int offset,int nbPage) {
		QComputer computer = QComputer.computer;
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		try {
			
			return (ArrayList<Computer>) query.from(computer).offset(offset).limit(nbPage).fetch();
		} catch (Exception e) {
			logger.error("Not able to find all pages");
			return null;
		}
		
		
		/*List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("offset", offset)
				.addValue("nbPage",nbPage);
		
		computers = jdbcTemplate.query(findAllPagesQ,vParams, new MapperRowComputer());
		return computers;*/
	}
	
	public List<Computer> findBySearch(int offset,int nbPage,String search){
		
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		try {
			return (ArrayList<Computer>) query.from(computer).where(computer.name.contains(search).or(company.name.contains(search))).offset(offset).limit(nbPage).fetch();	
		} catch (Exception e) {
			logger.error("Find by search");
			return null;
		}
		
		/*List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				.addValue("search", "%"+search+"%")
				.addValue("offset", offset)
				.addValue("nbPage",nbPage);
	
		computers = jdbcTemplate.query(findSearch,vParams, new MapperRowComputer());
		return computers;*/
	}
	public List<Computer> findOrder(Page page,String order,String ascending){
		
		QComputer computer = QComputer.computer;
		
		JPAQuery<Computer> query = new JPAQuery<Computer>(entityManager);
		try {
			return (ArrayList<Computer>) query.from(computer).orderBy(orderByConversionQ(order,ascending)).offset(page.getOffset()).limit(page.getNbPages()).fetch();
		} catch (Exception e) {
			logger.error("findOrder");
			return null;
		}
		
		
	
		
		/*List<Computer> computers = new ArrayList<Computer>();
		
		MapSqlParameterSource vParams = new MapSqlParameterSource()
				
				.addValue("getOffset", page.getOffset())
				.addValue("getNbPages",page.getNbPages());
		computers = jdbcTemplate.query("SELECT computer.id,computer.name,introduced,discontinued,company_id,c.name FROM computer LEFT JOIN company as c on computer.company_id = c.id ORDER BY "+order+" "+ascending+" LIMIT :getOffset, :getNbPages",vParams, new MapperRowComputer());
		return computers;*/
	}
	
	private OrderSpecifier<?> orderByConversionQ(String order,String ascending) {
		if(order==null) {
			return QComputer.computer.id.asc();
		}
		if(order.equals("computer.name")) {
			if(ascending.equals("ASC")) {
				return QComputer.computer.name.asc();
			}
			else if(ascending.equals("DESC")) {
				return QComputer.computer.name.desc();
			}
		}
		else if(order.equals("computer.introduced")) {
			if(ascending.equals("ASC")) {
				return QComputer.computer.introduced.asc();
			}
			else if(ascending.equals("DESC")) {
				return QComputer.computer.introduced.desc();
			}
		}
		else if(order.equals("computer.discontinued")) {
			if(ascending.equals("ASC")) {
				return QComputer.computer.discontinued.asc();
			}
			else if(ascending.equals("DESC")) {
				return QComputer.computer.discontinued.desc();
			}
		}
		else if(order.equals("c.name")) {
			if(ascending.equals("ASC")) {
				return QComputer.computer.company.name.asc();
			}
			else if(ascending.equals("DESC")) {
				return QComputer.computer.company.name.desc();
			}
		}
		
		return QComputer.computer.id.asc();
	}
	

	
	

}
