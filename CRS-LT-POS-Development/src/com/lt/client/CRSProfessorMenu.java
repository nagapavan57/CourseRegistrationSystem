package com.lt.client;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.StudentsEnrolled;
import com.lt.business.ProfessorImplService;
import com.lt.exception.GradeNotAddedException;
import com.lt.validator.ProfessorValidator;

public class CRSProfessorMenu {
	ProfessorImplService profInterfaceImpl = new ProfessorImplService();
	private static Logger logger = Logger.getLogger(CRSProfessorMenu.class);
	
	public void mainMenu(String userId) {
		
		Scanner sc =  new Scanner(System.in);
		while(true){
			System.out.println("Welcome to Professor menu : ");
			System.out.println("1. view Enrolled Students");
			System.out.println("2. Add Grade");
			System.out.println("3. viewOfferedCourses");
			System.out.println("4. Exit");
			System.out.println("Enter your choice");
		int input = sc.nextInt();
		
			switch (input) {
			case 1:
				viewEnrolledStudents(userId);
				break;
			case 2:
				addGrade(userId);
				break;
			case 3:
				viewCoursesOffered(userId);
				break;
			default:
				break;
			}
			if(input==4){
				System.out.println("You Have Successfully Logedout!!!\n");
				break;
			}
		}
	}

	private void viewCoursesOffered(String userId) {
		try{
		List<Course> courseOffered = profInterfaceImpl.viewRegisteredCourses(userId);
		System.out.println(String.format("|%-10s | %-10s|","-----------","-----------"));
		System.out.println(String.format("|%-10s | %-10s|","COURSE CODE","COURSE CODE"));
		System.out.println(String.format("|%-10s | %-10s|","-----------","-----------"));
		courseOffered.forEach(c -> System.out.println(String.format("|%-11s | %-10s |",c.getCourseName(),c.getCourseCode())));
			for(Course c:courseOffered){
				System.out.println(String.format("|%-11s | %-10s |",c.getCourseName(),c.getCourseCode()));
			}
		}catch(SQLException e){
			logger.error(e.getMessage());
		}
		
	}

	private void addGrade(String profId) {
		
		Scanner sc=new Scanner(System.in);
		
		int studentId;
		String courseCode,grade,courseName;
		try
		{
			List<StudentsEnrolled> enrolledStudents=new ArrayList<StudentsEnrolled>();
			enrolledStudents=viewEnrolledStudents(profId);
			
			List<Course> coursesEnrolled=new ArrayList<Course>();
			coursesEnrolled	=profInterfaceImpl.viewRegisteredCourses(profId);
			System.out.println("**************Add Grade****************");
			System.out.println("Enter student id");
			studentId=sc.nextInt();
			System.out.println("Enter course code");
			courseCode=sc.next();
			System.out.println("Enter course Name");
			courseName=sc.next();
			System.out.println("Enter grade");
			grade=sc.next();
			if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))
			{
				profInterfaceImpl.addGrade(studentId, courseCode,courseName, grade);
				//System.out.println("Grade added successfully for "+studentId+" with course code "+courseCode);
			}
			else
			{
				logger.error("Invalid data entered, try again!");
			}
		}
		catch(GradeNotAddedException | SQLException ex)
		{
			logger.error("Grade cannot be added for Student!!!");
			
		}
		
		
	}

	public List<StudentsEnrolled> viewEnrolledStudents(String profId) {
		//List<Course> coursesEnrolled=profInterfaceImpl.viewRegisteredCourses(profId);
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","-----------","----------" ));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","COURSE CODE","COURSE CODE","STUDENT ID" ));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","-----------","----------" ));
		List<StudentsEnrolled> enrolledStudents=new ArrayList<StudentsEnrolled>();
		try
		{
			
			enrolledStudents=profInterfaceImpl.viewEnrolledStudents(profId);
			enrolledStudents.forEach(obj -> System.out.println(String.format("|%-11s | %-11s | %-10s|",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId())));
			
		}
		catch(SQLException ex)
		{
			logger.error("Oops!!! Please try again later!"+ex.getMessage());
		}
		return enrolledStudents;
	}

}
