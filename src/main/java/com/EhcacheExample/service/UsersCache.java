package com.EhcacheExample.service;

import java.util.List;

import com.EhcacheExample.model.Users;


public interface UsersCache {

	Users getUser(String name);

	List<Users> getAllRecords();

}
