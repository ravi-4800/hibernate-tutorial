package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	
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
			
//			System.out.println("\nDeleting student...");
//			session.delete(myStudent);
			
			
			// Deleting student id = 2
			System.out.println("\nDeleting student id = 2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			
		} finally {
			factory.close();
		}
	}
}
