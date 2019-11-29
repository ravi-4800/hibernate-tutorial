package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			// start transaction
			
			session.beginTransaction();
			
			System.out.println("\nGetting student  with id: " + studentId);
			
			// read the object based on the id: primary key
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("\nUpdating student...");
			myStudent.setFirstName("Scooby");
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			// New Code 
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			// update email for all students
			session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			
		} finally {
			factory.close();
		}
	}
}
