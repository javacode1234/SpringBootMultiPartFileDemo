package com.multipartfiledemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multipartfiledemo.model.Employee;
import com.multipartfiledemo.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeService es;
	
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", es.getAllEmployees());
		return "employeepage";
	}
	
	@GetMapping("/newemployee")
	public String getNewEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "newemployee";
	}
	
	@PostMapping("/save-employee")
	public String saveEmployee(@ModelAttribute("employee")Employee emp) {
		es.saveEmployee(emp);		
		return "redirect:/employees";
	}
	
	@GetMapping("/get-employee/{id}")
	public String getEmployeeById(@PathVariable("id")Long id, Model model) {
		Employee emp = es.getEmployeeById(id);
		model.addAttribute("employee", emp);
		return "editemployee";
	}
	
	@PostMapping("/update-employee/{id}")
	public String updateEmployeeById(@PathVariable("id")Long id, @ModelAttribute("employee")Employee emp, Model model) {
		
		Employee existEmp = es.getEmployeeById(id);
		existEmp.setFirstName(emp.getFirstName());
		existEmp.setLastName(emp.getLastName());
		existEmp.setEmail(emp.getEmail());		
		existEmp.setSalary(emp.getSalary());
		
		es.saveEmployee(existEmp);
		return "redirect:/employees";
	}
	
	@GetMapping("/delete-employee/{id}")
	public String delEmployeeById(@PathVariable("id")Long id) {
		es.deleteEmployeeById(id);
		return "redirect:/employees";
	}
	
	
	
}
