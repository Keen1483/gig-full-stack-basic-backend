package com.devskills.gigfullstackbasic.service;

import java.util.List;

import com.devskills.gigfullstackbasic.model.User;

public interface UserService {
	List<User> getUsers();
	User getUser(String username);
	User saveUser(User user);
	User updateUser(Long id, User user);
	void deleteUser(String username);
}
