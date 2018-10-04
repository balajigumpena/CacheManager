package com.cache.application;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String USERS_CACHE = "allUsers";
    
    @Bean
    public CacheManager cacheManager() {
       GuavaCacheManager cacheManager = new GuavaCacheManager(USERS_CACHE);
       CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
														       .maximumSize(100)
														       .expireAfterWrite(1, TimeUnit.HOURS);
       cacheManager.setCacheBuilder(cacheBuilder);
       return cacheManager;
    }

}
