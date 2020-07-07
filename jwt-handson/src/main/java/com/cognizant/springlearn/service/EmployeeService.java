package com.cognizant.springlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.repository.EmployeeDao;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	public List<Employee> getAllEmployees(){
		return employeeDao.getAllEmployees();
	}

	public Employee deleteEmployee(int id) throws EmployeeNotFoundException {
		return employeeDao.deleteEmployee(id);
	}
}
