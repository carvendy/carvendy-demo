package com.carvendy.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.carvendy.jpa.dao.StudentDao;
import com.carvendy.jpa.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Override
	public Student insert(Student stu) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA"); 
		 EntityManager em = emf.createEntityManager(); 
		 em.getTransaction().begin(); 
		 em.persist(stu); 
		 em.getTransaction().commit(); 
		 emf.close(); 
		return stu;
	}

}
