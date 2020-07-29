package com.excilys.formation.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.GET,"/","/dashboard").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/","/dashboard").hasRole("ADMIN")
		.antMatchers("/addComputer").hasRole("ADMIN")
		.antMatchers("/editComputer").hasRole("ADMIN")
		.and()
		.formLogin()
		.and()
		.logout()
		.and().csrf().disable();
	}
	
	@Bean
    public PasswordEncoder encoder() {
		System.out.println(new BCryptPasswordEncoder().encode("te"));
        return new BCryptPasswordEncoder();
    }

}
