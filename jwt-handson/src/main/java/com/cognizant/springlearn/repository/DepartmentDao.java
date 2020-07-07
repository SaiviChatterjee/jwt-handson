package com.cognizant.springlearn.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.model.Department;

@Repository
public class DepartmentDao {
	private static ArrayList<Department> DEPARTMENT_LIST;
	public DepartmentDao() {
		ApplicationContext context=new ClassPathXmlApplicationContext("employee.xml");
		DEPARTMENT_LIST=(ArrayList)context.getBean("departmentList");
	}
	public List<Department> getAllDepartments(){
		return DEPARTMENT_LIST;
	}
}
