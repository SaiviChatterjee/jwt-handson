package com.cognizant.springlearn.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Repository
public class EmployeeDao {
	private static ArrayList<Employee> EMPLOYEE_LIST;
	public EmployeeDao() {
		ApplicationContext context=new ClassPathXmlApplicationContext("employee.xml");
		EMPLOYEE_LIST=(ArrayList)context.getBean("employeeList");
	}
	public List<Employee> getAllEmployees(){
		return EMPLOYEE_LIST;
	}
	public Employee deleteEmployee(int id) throws EmployeeNotFoundException {
		for(Employee employee:EMPLOYEE_LIST) {
			if(employee.getId()==id) {
				EMPLOYEE_LIST.remove(employee);
				return employee;
			}
		}
		throw new EmployeeNotFoundException();
	}
}
