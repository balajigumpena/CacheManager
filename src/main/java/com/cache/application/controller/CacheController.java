package com.cache.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cache.application.model.User;
import com.cache.application.service.CacheService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CacheController
{
	private final CacheService cacheService;
    
	@GetMapping(value = "/")
    public List<User> getUsers(){
	   List<User> users = cacheService.getUsers();
	   log.info(users.toString());
	   return users;
    }
	@GetMapping(value = "/name/{name}")
    public User getUserByName(@PathVariable("name") String name){
	   User user = cacheService.getUser(name);
	   return user;
    }
	@GetMapping(value = "/state/{st}")
    public User getUserByCountry(@PathVariable("st") String state){
	   User user = cacheService.getUserByState(state).orElse(null);
	   return user;
    }
}
