package com.stackroute.userservice.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<User> u1 = userRepo.findById(user.getUserId());
		if(u1.isPresent())
		{
			throw new UserAlreadyExistsException("User with id already Exists");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserIdAndPassword(userId, password);
		
		if(user == null)
		{
			throw new UserNotFoundException("User Id and Password Mismatch");
		}
		return user;
	}

}
