package com.cache.application;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String USERS_CACHE = "allUsers";
    public static final String USER_BY_STATE_CACHE = "userByState";
    
    @Bean
    public CacheManager cacheManager() {
       SimpleCacheManager cacheManager = new SimpleCacheManager();       
       GuavaCache allUsersCache = new GuavaCache(USER_BY_STATE_CACHE, makeDefaultCache());
       GuavaCache userByNameCache = new GuavaCache(USERS_CACHE, makeDefaultCache());
       List<GuavaCache> caches = Arrays.asList(allUsersCache,userByNameCache);
       /*GuavaCacheManager cacheManager = new GuavaCacheManager(USERS_CACHE);
       CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
														       .maximumSize(100)
														       .expireAfterWrite(1, TimeUnit.HOURS);
       cacheManager.setCacheBuilder(cacheBuilder);*/
       cacheManager.setCaches(caches);
       return cacheManager;
    }
    private Cache<Object, Object> makeDefaultCache(){
    	return CacheBuilder.newBuilder()
			       .maximumSize(100)
			       .expireAfterWrite(1, TimeUnit.HOURS)
			       .build();
    }

}
