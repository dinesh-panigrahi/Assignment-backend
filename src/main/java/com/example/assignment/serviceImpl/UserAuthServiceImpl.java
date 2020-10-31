package com.example.assignment.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.assignment.entity.User;
import com.example.assignment.repo.UserRepo;

@Service
public class UserAuthServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByEmail(emailId);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(emailId, user.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with emailId: " + emailId);
		}
	}

}