package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int theId);
    public void save (Employee theEmployee);
    public Employee deleteById(int theId);
}
