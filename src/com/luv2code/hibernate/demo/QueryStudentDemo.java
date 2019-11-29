package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void displayStudents(List<Student> students) {
		for(Student tempStudent: students) {
			System.out.println(tempStudent);
		}
	}
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// Query students: lastName = Duck
			theStudents = session.createQuery("from Student s where s.lastName = 'Duck'").getResultList();
			
			// display the students who have last name of Duck
			displayStudents(theStudents);
			
			// Query Students: lastName = Duck or firstName = John
			theStudents = session.createQuery("from Student s where "
					+ "s.lastName = 'Duck' OR s.firstName = 'John'").getResultList();
			
			// display the students who have lastName of Duck or firstName of John
			displayStudents(theStudents);
			
			// Query Students where email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%luv2code.com'").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
}
