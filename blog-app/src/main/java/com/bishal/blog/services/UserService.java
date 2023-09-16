package com.bishal.blog.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bishal.blog.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
