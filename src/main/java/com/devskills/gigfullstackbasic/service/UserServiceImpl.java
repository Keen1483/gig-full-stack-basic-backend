package com.devskills.gigfullstackbasic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devskills.gigfullstackbasic.exception.NoContentException;
import com.devskills.gigfullstackbasic.exception.ResourceNotFoundException;
import com.devskills.gigfullstackbasic.model.User;
import com.devskills.gigfullstackbasic.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}", username);
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			log.error("User {} not found in the database", username);
			throw new ResourceNotFoundException("User " + username + " not found in the database");
		}
	}

	@Override
	public User saveUser(User user) {
		if (user.getEmail() == null || user.getPassword() == null) {
			throw new NoContentException("Cannot create empty user: " + user.toString());
		} else {
			log.info("Saving new user {} in the database", user.getUsername());
			String username = user.getUsername() == null ? user.getEmail() : user.getUsername();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		}
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
			User currentUser = optionalUser.get();
			
			String email = user.getEmail();
			if (email != null) {
				currentUser.setEmail(email);
			}
			
			String username = user.getUsername();
			if (username != null) {
				currentUser.setUsername(username);
			}
			
			String password = user.getPassword();
			if (password != null) {
				currentUser.setPassword(passwordEncoder.encode(password));
			}
			
			return userRepository.save(currentUser);
		} else {
			log.error("User {} not found in database", user.getUsername());
			throw new ResourceNotFoundException("User " + user.getUsername() + " not found in database");
		}
	}

	@Override
	public void deleteUser(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		if (optionalUser.isPresent()) {
			log.warn("Deleting user {} in the database", username);
			userRepository.deleteByUsername(username);
		} else {
			log.error("User {} not found in the database", username);
			throw new ResourceNotFoundException("User " + username + " not found in the database");
		}
	}

}
