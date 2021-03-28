package com.cloudzoneInfoConnect.WebApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudzoneInfoConnect.WebApp.exception.ResourceNotFoundException;
import com.cloudzoneInfoConnect.WebApp.model.Employee;
import com.cloudzoneInfoConnect.WebApp.model.Login;
import com.cloudzoneInfoConnect.WebApp.repository.EmployeeRepository;
import com.cloudzoneInfoConnect.WebApp.repository.LoginRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepository.findAll();
	}
	
	// Employee Rest aPI
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
		
	// Get employee details by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable Long id) {
		String empId = String.valueOf(id);
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Not data found for Id"));
		
		return ResponseEntity.ok(employee);
	}
	
	// Update employee API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee updateEmployee){
		String empId = String.valueOf(id);
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Not data found for Id"));
		
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastname(updateEmployee.getLastName());
		employee.setEmailId(updateEmployee.getEmailId());
		
		Employee employeeDetails= employeeRepository.save(employee);
		return ResponseEntity.ok(employeeDetails);
	}
	
	
	// delete employee rest api
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		String empId = String.valueOf(id);
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	// Login user verification API
	@PostMapping("/user/login")
	public ResponseEntity<Login> loginUser(@RequestBody Login loginData) throws ResourceNotFoundException 	{
		Login loginUser = loginRepository.findByEmail(loginData.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("Email ID or Password not present please register"));
		if(loginUser.getEmail().equals(loginData.getEmail()) && loginUser.getPassword().equals(loginData.getPassword()))
		return ResponseEntity.ok(loginUser);
		
		else {
			 throw new ResourceNotFoundException("Emaild or password does not match");
		}
	}
	
}
