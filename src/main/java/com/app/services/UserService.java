package com.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.User;
import com.app.repository.UserPaginationRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserService {
   
	@Autowired
	private  UserRepository repo;
	
	@Autowired
	private UserPaginationRepository pagerepo;
	
	public void saveMyuser(User user)
	{
		repo.save(user);
	}
	
	public List<User> showAllUsers(){
		
		//Pageable pageble=PageRequest.of(0, 3);
		List<User> users = new ArrayList<User>();
		for(User user : repo.findAll()) {
			users.add(user);
		}	
		return users;
	}
	
	
	public void deleteMyUser(int id) {
		repo.deleteById(id);
	}
	
	
	public User editUser(int id)
	{	
        Optional<User> user= repo.findById(id);
	    return user.get();
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
	
    public Page<User> pageList()
    {
    	Pageable pageable=PageRequest.of(0, 5);
		return pagerepo.findAll(pageable);
    	
    }
	
}
