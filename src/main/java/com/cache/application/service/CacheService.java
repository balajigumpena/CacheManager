package com.cache.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import com.cache.application.CacheConfig;
import com.cache.application.model.User;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CacheService
{
	List<User> users = Arrays.asList(new User("Balaji","MH"),new User("Kumar","RJ"));
	LoadingCache<String, Optional<User>> userCache = CacheBuilder.newBuilder()
														         .concurrencyLevel(Runtime.getRuntime().availableProcessors())
														         .maximumSize(100)                             // maximum 100 records can be cached
														         .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
														         .build(CacheLoader.from(this::getUserByName));
	
   @Cacheable(value = CacheConfig.USERS_CACHE)
   public List<User> getUsers(){
	   log.info("Getting users");
	   return users;
   }
   public User getUser(String name){
	 try {
		Optional<User> optional = userCache.get(name);
		if(optional.isPresent()){
			return optional.get();
		}
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
   }
   @Cacheable(value = CacheConfig.USER_BY_STATE_CACHE)
   public Optional<User> getUserByState(String state){
	   log.info("Finding user by state");
	   return users.stream()
			   	   .filter(usr -> usr.getState().equals(state))
			   	   .findFirst();
	   }
   
   
   private Optional<User> getUserByName(String userName){
	   log.info("Finding user by name");
	   return users.stream()
			   	   .filter(usr -> usr.getName().equals(userName))
			   	   .findFirst();	   
   }
   
}
