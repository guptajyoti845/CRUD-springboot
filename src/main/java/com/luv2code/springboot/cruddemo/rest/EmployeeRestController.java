package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	//add mapping for getting specific Employee detail

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId){
		Employee theEmployee =employeeService.findById(employeeId);
		if(theEmployee == null){
			throw new RuntimeException("EmployeeId not found -"+employeeId);
		}
		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee){
		//also just in case they pass an id or json .....set up id to 0
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}

	//add mapping for 	PUT Call /employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee){
		employeeService.save(theEmployee);
		return theEmployee;
	}

	@DeleteMapping("/employees")
	public String deleteEmployee(@RequestParam int  theEmployeeId){
		Employee tempEmployee = employeeService.findById(theEmployeeId);

		// throw exception if null

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + theEmployeeId);
		}

		employeeService.deleteById(theEmployeeId);

		return "Deleted employee id - " + theEmployeeId;
	}
}










