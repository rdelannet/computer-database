package com.excilys.formation.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.UserDTO;
import com.excilys.formation.model.User;

public class UserMapper {
	
	 private static Logger logger = LoggerFactory.getLogger(UserMapper.class);
	 
	 public static User dtoToUser(UserDTO dto) {
		 User user = new User();
		 
		 try {
			 if (dto.getId() != null && !dto.getId().isEmpty()) {
	                user.setId(Integer.valueOf(dto.getId()));
	            }
			 user.setUsername(dto.getUsername());
	         user.setPassword(dto.getPassword());
	         user.setRole(dto.getRole());
			
		} catch (NullPointerException  e) {
			logger.error("can't convert userDTO to User", e);
		}
		 return user;
	 }

}
