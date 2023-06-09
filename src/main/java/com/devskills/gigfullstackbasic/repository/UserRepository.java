package com.devskills.gigfullstackbasic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devskills.gigfullstackbasic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	void deleteByUsername(String username);

}
