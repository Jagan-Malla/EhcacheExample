package com.EhcacheExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EhcacheExample.dto.Employee;
import com.EhcacheExample.dto.UserDto;
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
    
    @GetMapping(value="InsertRecord")
    public String inserRecord(@RequestBody UserDto userDto) {
    	return usersCache.saveRecord(userDto);
    }
   @PostMapping(value ="Test")
   @CrossOrigin
   @ResponseBody
   public Employee test(@RequestBody Employee e) {
	   Employee e1 =new Employee();
	   e1.setFirstName("TestFn");
	   e1.setLastName("TestLn");
	   e1.setEmailId("jagan@gmail.com");
	   return e1;
   }

}
