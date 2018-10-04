package com.cache.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
	@GetMapping(value = "/guava")
    public List<User> getUsers(){
	   List<User> users = cacheService.getUsers();
	   log.info(users.toString());
	   return users;
    }
}
