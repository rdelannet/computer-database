package com.excilys.formation.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MainWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		  AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		        
		  webContext.register(WebConfig.class, MainWebAppInitializer.class, SpringConf.class);
		  webContext.setServletContext(servletContext);
		 
		  ServletRegistration.Dynamic servlet=servletContext.addServlet("dynamicServlet",new DispatcherServlet(webContext));
	      servlet.setLoadOnStartup(1);
	      servlet.addMapping("/");
	}
	

}
