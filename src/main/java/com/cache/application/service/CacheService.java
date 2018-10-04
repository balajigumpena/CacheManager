package com.cache.application.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;

import com.cache.application.CacheConfig;
import com.cache.application.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CacheService
{
   @Cacheable(value = CacheConfig.USERS_CACHE)
   public List<User> getUsers(){
	   log.info("Getting users");
	   return Arrays.asList(new User("Balaji","MH"));
   }
   
}
