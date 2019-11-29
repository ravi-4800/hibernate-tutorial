package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	
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
			Student student = new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("saving student object...");
			System.out.println(student);
			session.save(student);
			
			// commit transaction
			session.getTransaction().commit();
			
			// MY NEW CODE
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + student.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			// read the object based on the id: primary key
			Student myStudent = session.get(Student.class, student.getId());
			
			System.out.println("Get Complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
