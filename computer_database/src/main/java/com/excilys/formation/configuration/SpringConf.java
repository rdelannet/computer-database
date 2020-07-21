package com.excilys.formation.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.excilys.formation")
public class SpringConf extends AbstractContextLoaderInitializer implements WebApplicationInitializer {

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConf.class);
		return rootContext;
	}
	@Bean
	public DataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/datasource.properties"));
	}
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		return new NamedParameterJdbcTemplate(hikariDataSource());
	}
	
	@Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(hikariDataSource());
	      em.setPackagesToScan(new String[]{ "com.excilys.formation" });

	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      return em;
	   }

}
