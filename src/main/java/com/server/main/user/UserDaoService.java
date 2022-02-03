package com.server.main.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService 
{
	public static int usersCount=5;
	
	//creating instance of an Array
	private static List<User> users= new ArrayList<>();
	
	//static block
	static
	{
		//adding users to the list
		users.add(new User(1,"Abdul",new Date()));
		users.add(new User(2,"kishan",new Date()));
		users.add(new User(3,"Anu",new Date()));
		users.add(new User(4,"Jay",new Date()));
		users.add(new User(5,"pallabi",new Date()));
	}
	
	public List <User> findAll()
	{
		return users;
	}
	
	public User save(User user)
	{
		if(user.getId()==null);
		{
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
		for(User user:users)
		{
			if(user.getId()==id)
				return user;
		}
		return null;
	}
	
}
