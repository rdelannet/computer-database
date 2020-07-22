package com.excilys.formation.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.excilys.formation")
public class MainWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		  AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		        
		  webContext.register(WebConfig.class, MainWebAppInitializer.class);
		  webContext.setServletContext(servletContext);
		 
		  ServletRegistration.Dynamic servlet=servletContext.addServlet("dynamicServlet",new DispatcherServlet(webContext));
	      servlet.setLoadOnStartup(1);
	      servlet.addMapping("/");
	}
	
	 @Bean
		public DataSource hikariDataSource() {
			return new HikariDataSource(new HikariConfig("/datasource.properties"));
		}
	 
	 @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(hikariDataSource());
	      em.setPackagesToScan(new String[]{ "com.excilys.formation.model" });

	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      return em;
	   }
	 	@Bean
		public PlatformTransactionManager transactionManager() {
		    JpaTransactionManager transactionManager = new JpaTransactionManager();
		    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		 
		    return transactionManager;
		}
		 
		@Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		    return new PersistenceExceptionTranslationPostProcessor();
		}
	

}
