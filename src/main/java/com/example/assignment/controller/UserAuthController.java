package com.example.assignment.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.config.JwtTokenUtil;
import com.example.assignment.dto.JwtRequest;
import com.example.assignment.dto.JwtResponse;
import com.example.assignment.dto.UserDto;
import com.example.assignment.entity.User;
import com.example.assignment.repo.UserRepo;
import com.example.assignment.service.UserService;
import com.example.assignment.serviceImpl.UserAuthServiceImpl;

@RestController
@CrossOrigin
public class UserAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserAuthServiceImpl userDetailsService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		User user = userRepo.findByEmail(authenticationRequest.getUserName()).get();

		return ResponseEntity.ok(new JwtResponse(token, user.getId()));
	}

	private void authenticate(String emailId, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Email/Password is not correct", e);
		}
	}

	@PostMapping("/registerUser")
	public Callable<ResponseEntity<UserDto>> saveUser(@RequestBody UserDto userDto) {

		return () -> {
			UserDto dto = userService.saveUser(userDto);
			return ResponseEntity.ok(dto);
		};
	}
}
