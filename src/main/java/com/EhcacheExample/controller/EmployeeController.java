package com.EhcacheExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EhcacheExample.exceptions.ResourceNotFoundException;
import com.EhcacheExample.model.Employee;
import com.EhcacheExample.repositories.EmployeeRepository;

@Controller
@RequestMapping(value ="employeeApi")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping(value ="/employee")
	@ResponseBody
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeRepository.save(emp);
	}
	
	@GetMapping(value ="/employee")
	@ResponseBody
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/viewEmployees")
	@ResponseBody
	public Employee getEmployeeById(@RequestParam String employeid) {
		Employee employee = employeeRepository.findById(Integer.valueOf(employeid))
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeid));
		return employee;
	}
	
	
	@PutMapping("/employee")
	@ResponseBody
	public Employee updateEmployee(@RequestParam String employeid, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(Integer.valueOf(employeid))
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeid));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}
	
	
	@DeleteMapping("/employee")
	@ResponseBody
	public  Boolean deleteEmployee(@RequestParam String employeid){
		Employee employee = employeeRepository.findById(Integer.valueOf(employeid))
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeid));
			employeeRepository.delete(employee);
			return true;
	}

}
