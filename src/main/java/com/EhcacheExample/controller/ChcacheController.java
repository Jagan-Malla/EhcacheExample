package com.EhcacheExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EhcacheExample.model.Users;
import com.EhcacheExample.service.UsersCache;


@Controller
@RequestMapping(value="chcacheTest")
public class ChcacheController {
	@Autowired
    UsersCache usersCache;

    @GetMapping(value = "/{name}")
    public Users getUser(@PathVariable final String name) {
        return usersCache.getUser(name);
    }
    
    @GetMapping(value="getallrecords")
    public List<Users> getAllRecords(){
    	return usersCache.getAllRecords();
    }
    
    @GetMapping(value="hello")
    public String viweTest() {
    	return "hello";
    }

}
