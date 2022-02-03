package com.server.main.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource 
{
	//White label Error
		private static final String PATH = "/error";
		public String error()
		{
			return "Error handling";
		}
		public String getErrorPath()
		{
			return PATH;
		}
		
	@Autowired
	private UserDaoService service;
	
	//Get ALL USERS
	@GetMapping("/users")
	public List<User> retriveAllUsers()
	{
		return service.findAll();
	}
	
	//Get Specific User
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id)
	{
		User user=service.findOne(id);
		if(user==null)
		{
			//Handling Exception
			throw new UserNotFoundException("id - " + id);
		}
		
		return service.findOne(id);
		
		
	}
	
	//URI LOCATION
	//Create User 
	@PostMapping("/users")
	public  ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User savedUser=service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		User user = service.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException(getErrorPath());
		}
	}
	
	
	
	
	
	

}
