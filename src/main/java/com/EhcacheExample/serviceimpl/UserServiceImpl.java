package com.EhcacheExample.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.EhcacheExample.model.Users;
import com.EhcacheExample.repositories.EhcacheRepository;
import com.EhcacheExample.service.UsersCache;

import net.sf.ehcache.CacheManager;



@Component
public class UserServiceImpl implements UsersCache{
	
	@Autowired
	EhcacheRepository ehcacheRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@Override
	@Cacheable(value = "usersCache", key = "#name")
	public Users getUser(String name) {
		Users us=ehcacheRepository.findByName(name);
		userServiceImpl.chcacheChecking();
		return us;
	}

	@Override
	public List<Users> getAllRecords() {
		List<Users> us=ehcacheRepository.findAll();
		userServiceImpl.chcacheChecking();
		return us;
	}
	
	public void chcacheChecking() {
		List<CacheManager> tempManagers = CacheManager.ALL_CACHE_MANAGERS;
		System.out.println("# of CMs : " + tempManagers.size());
		for (CacheManager tempCM : tempManagers) {
		        System.out.println("Got: " + tempCM.getName());
		        String[] cacheNames = tempCM.getCacheNames();
		        for (int i = 0; i < cacheNames.length; i++) {
		            String cacheName = cacheNames[i];
		            System.out.println(cacheName+" - "+ tempCM.getEhcache(cacheName).getStatistics().toString());
		        }
		}
	}

}
