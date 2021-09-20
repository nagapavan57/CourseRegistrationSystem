package com.lt.client;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;







import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.business.AdminImplService;
import com.lt.constants.Role;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.CourseFoundException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.ProfessorNotAddedException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

public class CRSAdminMenu {
	AdminImplService admin = new AdminImplService();
	AdminDaoImpl adminimpl =AdminDaoImpl.getInstance();
	private static Logger logger = Logger.getLogger(CRSAdminMenu.class);
	
	Scanner sc = new Scanner(System.in);
	public void mainMenu() {
		
		
		while(true){
			System.out.println("Welcome to Admin: ");
			System.out.println("1. Add course");
			System.out.println("2. Delete course");
			System.out.println("3. Approve Student");
			System.out.println("4. Add Professor");
			System.out.println("5. Assign professor");
			System.out.println("6. ViewCoursesIn Catalog");
			System.out.println("7. Exit!");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
				switch (choice) {
				case 1:
					addCourse();
					break;
		
				case 2:
					deleteCourse();
					break;
		
				case 3:
					approveStudent();
					break;
		
				case 4:
					addProfessor();
					break;
		
				case 5:
					assignCourseToProf();
					break;
				case 6:
					viewCoursesInCatalogue();
					break;
		
				default:
					break;
				}
				if(choice==7){
					System.out.println("You Have Successfully Loggedout!!!\n");
					break;
				}
		}
		
	}

	
	


	private void assignCourseToProf(){
		List<Professor> professorList= admin.viewProfessors();
		System.out.println("**********Professors**********");
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","----","-------"));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","PROFESSORID","NAME","SUBJECT"));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","----","-------"));
		//professorList.forEach(System.out.format("|%-11s | %-10s | %-10s|", "professor")::println);
		professorList.forEach(professor -> System.out.println(String.format("|%-11s | %-10s | %-10s|", professor.getUserId(), professor.getName(), professor.getSubject())));
		
		/*
		 * for(Professor professor : professorList) {
		 * System.out.println(String.format("|%-11s | %-10s | %-10s|",
		 * professor.getUserId(), professor.getName(), professor.getSubject())); }
		 */
		 
		
		
		System.out.println("\n");
		List<Course> courseList=adminimpl.viewCourses();
		System.out.println(String.format("|%-10s | %-10s|","-----------", "-----------"));
		System.out.println(String.format("|%-10s | %-10s|","COURSE CODE", "COURSE NAME"));
		System.out.println(String.format("|%-10s | %-10s|","-----------", "-----------"));
		courseList.forEach(course -> System.out.println(String.format("|%-11s | %-11s|", course.getCourseCode(), course.getCourseName())));
			/*
			 * for(Course course : courseList) {
			 * System.out.println(String.format("|%-11s | %-11s|", course.getCourseCode(),
			 * course.getCourseName())); }
			 */
		Scanner assignCrse = new Scanner(System.in);
		System.out.println("Enter Course Code");
		String crseName=assignCrse.next();
		System.out.println("Enter Professor User Id");
		String prfId = assignCrse.next(); 
		try {
			admin.assignCourse(crseName, prfId);
		} catch (CourseNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	private void addProfessor() {
		Professor prof = new Professor();
		System.out.println("Enter Professor UserId");
		String userId = sc.next();
		prof.setUserId(userId);
		System.out.println("Enter Professor Password");
		String password = sc.next();
		prof.setPassword(password);
		System.out.println("Enter professor name....");
		String pname=sc.next();
		prof.setName(pname);
		System.out.println("Enter professor department....");
		String dname=sc.next();
		prof.setDepartment(dname);
		System.out.println("Enter professor subject....");
		String sub=sc.next();
		prof.setSubject(sub);
		try {
			admin.addProfessor(prof);
			//System.out.println("Professor Added Successfully\n");
		} catch (ProfessorNotAddedException e) {
			e.printStackTrace();
		}
	}

	private void addCourse(){
		List<Course> courseList=adminimpl.viewCourses();
		System.out.println("Enter Course code....");
		String coursecode=sc.next();
		System.out.println("Enter Course name....");
		String coursename=sc.next();
		System.out.println("Enter No.of Course Seats....");
		String seats=sc.next();
		System.out.println("Enter  Course Fee....");
		String fee=sc.next();
		Course course=new Course(coursecode, coursename, null, seats);
		try {
			admin.addCourse(courseList,course,fee);
		} catch (CourseFoundException e) {
			e.printStackTrace();
		}
		
	}

	


	private void deleteCourse() {
		List<Course> courseList = viewCoursesInCatalogue();
		System.out.println("Enter course code to drop");
		String courseCodeDrop = sc.next();
		
		try {
			admin.deleteCourse(courseCodeDrop, courseList);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void approveStudent(){
		List<Student> studentList = viewPendingAdmissions();
		if(studentList.size() == 0) {
			System.out.println("There are no pending Students to Approve!!");
			return;
		}
		System.out.println(String.format("%10s | %10s | %10s","STUDENT ID", "NAME", "BRANCH"));
		System.out.println(String.format("%10s | %10s | %10s","----------", "----", "------"));
		studentList.forEach(st -> System.out.println(String.format("%10s | %10s | %10s",st.getStudentId(),st.getName(),st.getBranchName())));
		
		Scanner approveStudInput = new Scanner(System.in);
		System.out.println("Enter Student Id");
		int id = approveStudInput.nextInt();
		try {
			admin.approveStudent(id, studentList);
		} catch (StudentNotFoundException e) {
		}
		//System.out.println("Student Approved Successfully\n");
		
	}
	
	private List<Course> viewCoursesInCatalogue() {
		List<Course> courseList = admin.viewCourses();
		if(courseList.size() == 0) {
			System.out.println("No course in the catalogue!");
			return courseList;
		}
		System.out.println(String.format("%10s | %10s | %10s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		System.out.println(String.format("%10s   %10s   %10s","-----------", "-----------", "----------"));
		courseList.forEach(course -> System.out.println(String.format("%10s | %10s | %10s", course.getCourseCode(), course.getCourseName(), course.getProfid())));
		
		return courseList;
		
	}
	private List<Student> viewPendingAdmissions() {
		
		List<Student> pendingStudentsList= admin.viewPendingAdmissions();
		if(pendingStudentsList.size() == 0) {
			return pendingStudentsList;
		}
		
		/*for(Student student : pendingStudentsList) {
			System.out.println(String.format("%20s | %20d | %20s | %20s", student.getUserId(), student.getStudentId(), student.getName(), student.getGender().toString()));
		}*/
		return pendingStudentsList;
	}
}