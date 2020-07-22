package com.excilys.formation.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.excilys.formation"})
public class SpringConf {

	@Bean
	public DataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/datasource.properties"));
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource hikariDataSource){
		return new NamedParameterJdbcTemplate(hikariDataSource);
	}
	
	@Bean
	public PlatformTransactionManager txManager(DataSource hikariDataSource) {
	    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
	    dataSourceTransactionManager.setDataSource(hikariDataSource);
	    return dataSourceTransactionManager;
	}
}
