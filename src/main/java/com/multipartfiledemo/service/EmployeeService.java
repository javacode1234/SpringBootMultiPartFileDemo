package com.multipartfiledemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multipartfiledemo.model.Employee;
import com.multipartfiledemo.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo er;
	
	public List<Employee> getAllEmployees(){
		return er.findAll();		
	}

	public void saveEmployee(Employee emp) {
		er.save(emp);		
	}

	public Employee getEmployeeById(Long id) {
		return er.findById(id).get();
	}

	public void deleteEmployeeById(Long id) {
		er.deleteById(id);
	}

	

}
