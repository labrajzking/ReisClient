package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entities.UserE;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService implements UserServiceI{
@Autowired
UserRepository userrepo;
@Autowired 
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Override
public void signup(UserE user) {
	user.setPassword(bCryptPasswordEncoder.encode((user.getPassword())));
	userrepo.save(user);
	
}
}
