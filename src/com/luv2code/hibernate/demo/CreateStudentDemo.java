package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/** use the session object to save the Java objects */
			
			// create the student object
			System.out.println("creating new student object...");
			Student student = new Student("Paul", "Wall", "paul@luv2code.com");
			Student student1 = new Student("John", "Doe", "john@luv2code.com");
			Student student2 = new Student("Tom", "Sawyer", "tom@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("saving student object...");
			session.save(student);
			session.save(student1);
			session.save(student2);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
