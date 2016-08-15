package com.carvendy.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carvendy.jpa.dao.StudentDao;
import com.carvendy.jpa.model.Student;
import com.carvendy.jpa.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao dao;
	
	@Transactional
	@Override
	public Student insert(String name, Integer age) {
		Student stu = new Student();
		stu.setName(name);
		stu.setAge(age);
		
		return dao.insert(stu);
	}

}
