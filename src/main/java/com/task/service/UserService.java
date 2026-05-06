package com.task.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Model.UserDetails;
import com.task.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
     private UserRepository repository;
     
     public UserDetails insertthedata(UserDetails ud)
     {
    	return repository.save(ud);
     }
     
     public Optional<UserDetails> findbyEmailidAndPassword(String emailid, String password)
     {
         return repository.findByEmailIdAndPassword(emailid, password);
     }
     
}
