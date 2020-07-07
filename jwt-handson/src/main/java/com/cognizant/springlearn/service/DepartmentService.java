package com.cognizant.springlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.repository.DepartmentDao;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	public List<Department> getAllDepartments(){
		return departmentDao.getAllDepartments();
	}
}
