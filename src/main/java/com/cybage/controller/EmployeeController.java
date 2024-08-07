package com.cybage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.entity.Employee;
import com.cybage.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws Exception{
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED); 
	}
	
	@GetMapping("/id/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId){
		return new ResponseEntity<>(employeeService.findEmployeeByEmployeeId(employeeId), HttpStatus.OK);
	}
}
