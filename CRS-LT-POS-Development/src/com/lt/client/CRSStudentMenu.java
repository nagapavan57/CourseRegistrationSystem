package com.lt.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.Grade;
import com.lt.business.NotificationImplService;
import com.lt.business.PaymentImplService;
import com.lt.business.ProfessorImplService;
import com.lt.business.SemisterRegistrationImplService;
import com.lt.constants.PaymentMode;
import com.lt.exception.CourseLimitExceedException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.SeatNotAvailableException;
import com.lt.validator.StudentValidator;

public class CRSStudentMenu {

	SemisterRegistrationImplService semiRegiImpl = new SemisterRegistrationImplService();
	PaymentImplService payment = new PaymentImplService();
	NotificationImplService notify  = new NotificationImplService();
	private boolean is_approved = false;
	private static Logger logger = Logger.getLogger(CRSStudentMenu.class);
	
	public void mainMenu(int studentId){

		is_approved = semiRegiImpl.getRegistrationStatus(studentId);

		Scanner studentInput = new Scanner(System.in);

		while (true) {

			System.out.println("Welcome to student menu : ");
			System.out.println("1. Register course");
			System.out.println("2. Drop course");
			System.out.println("3. View Available Courses");
			System.out.println("4. view Registered courses");
			System.out.println("5. Make payment");
			System.out.println("6. View Report card");
			System.out.println("7. Exit!!");
			System.out.println("Enter your choice");
			String choice = studentInput.next();
			switch (choice) {
			case "1":
				registerCourse(studentId);
				break;

			case "2":
				dropCourse(studentId);
				break;

			case "3":
				viewCourses(studentId);
				break;

			case "4":
				viewRegisteredCourse(studentId,0);
				break;

			case "5":
				makePayment(studentId);
				break;

			case "6":
				viewGradeCard(studentId);
				break;

			default:
				System.out.println("Invalid choice");
				break;

			}
			if (choice.equals("7")) {
				System.out.println("You have Succesfully LoggedOut!!\n");
				break;
			}

		}

	}

	private void registerCourse(int studentId){
		if (is_approved) {
			Scanner sc = new Scanner(System.in);
			List<Course> courseList = viewCourses(studentId);
			List<Course> registeredCourses = viewRegisteredCourse(studentId,1);
			
			int count = registeredCourses.size();
			while (count < 6) {
				try {
					
					if(courseList==null)
						System.out.println("You have No Courses To Register");
						
					if (courseList == null)
						//System.out.println("You have No Course To Register");
						return;

					System.out.println("Enter " + (count + 1)+ "st Course Code :");
					String courseCode = sc.next();
					System.out.println("Enter " + (count + 1)+ "st Course Name :");
					String courseName= sc.next();

					if (semiRegiImpl.addCourse(courseCode, courseName,studentId)) {
						System.out.println("Course " + courseCode
								+ " registered sucessfully.\n");
						count++;
					} else {
						System.out.println(" You have already registered for Course : "
										+ courseCode);
					}
				} catch (CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException  e) {
					logger.error(e.getMessage());
				}
			}
			if (count > 6) {
				System.out.println("You can choose only 6 courses!!!");
			}
			System.out.println("All Courses Registered Successfully!!!");
		} else {
			System.out.println("Please Do Signup First and Register Course");
		}

	}

	private void dropCourse(int studentId){
		Scanner sc = new Scanner(System.in);
		if (is_approved) {
			List<Course> registeredCourseList = viewRegisteredCourse(studentId,0);

			if (registeredCourseList == null)
				return;

			System.out.println("Enter the Course Code : ");
			String courseCode = sc.next();

			try {
				semiRegiImpl.dropCourse(studentId, courseCode,
						registeredCourseList);
				System.out.println("You have successfully dropped Course : "
						+ courseCode);

			} catch (CourseNotFoundException | SQLException e) {
				logger.error(e.getMessage());
			}

		} else {
			System.out.println("Please Do registration");
		}

	}

	/*
	 * private static void addCourse(int studentId,List<Course> courseList) {
	 * 
	 * Scanner addCourseInput = new Scanner(System.in);
	 * System.out.println("Enter course code"); String courseCode =
	 * addCourseInput.next(); System.out.println("Enter course name"); String
	 * courseName = addCourseInput.next();
	 * System.out.println("Enter instructor name"); String instructor =
	 * addCourseInput.next(); System.out.println("Enter duration in months");
	 * String duration = addCourseInput.next(); StudentImplService studentImpl =
	 * new StudentImplService(); studentImpl.addCourse(courseCode, courseName,
	 * instructor, duration, courseList);
	 * 
	 * }
	 */

	private void viewGradeCard(int studentId) {
		List<Grade> reportCard = null;
		try {
			reportCard = semiRegiImpl.viewGradeCard(studentId);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		if (reportCard.isEmpty()) {
			System.out
					.println("You haven't registered for any courses. Please Register First!!");
			return;
		}
		System.out.println("-------------Report Card----------------");
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","-----------","--------------" ));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","COURSE CODE","COURSE CODE","GRADE OBTAINED"));
		System.out.println(String.format("|%-10s | %-10s | %-10s|","-----------","-----------","--------------" ));
		for (Grade grd : reportCard) {
			System.out.println(String.format("|%-11s | %-11s | %-10s|",
					grd.getCourseCode(), grd.getCourseName(), grd.getGrade()));
		}

	}

	private void makePayment(int studentId) {
		Scanner sc = new Scanner(System.in);
		double fee = 0.0;
		String cardNo=null;
		String Expiry=null;
		String CVV=null;
		try {
			fee = semiRegiImpl.calculateFee(studentId);
		} catch (SQLException e) {

			logger.error(e.getMessage());
		}

		if (fee == 0.0) {
			System.out.println("You have not  registered for any courses yet");
		} else {

			System.out.println("Your total fee  = " + fee);
			System.out.println("Want to continue Fee Payment(y/n)");
			String ch = sc.next();
			if (ch.equals("y")) {
				System.out.println("Select Mode of Payment:");

				int index = 1;
				
				 for(PaymentMode mode : PaymentMode.values()) {
					 System.out.println(index + " " + mode);
					 index = index + 1; 
					 }
				 
				 PaymentMode mode = PaymentMode.getModeofPayment(sc.nextInt());
				 if(mode.toString().equals("CREDIT_CARD") || mode.toString().equals("DEBIT_CARD")){
					 System.out.println("Enter Credit/Debit Card Number");
					  cardNo = sc.next();
					  if(!StudentValidator.isValidCard(cardNo)){
						  logger.error("Please Enter Valid Card");
					  }  
					 System.out.println("Enter Expiry of Card(MM/YYYY)");
					  Expiry = sc.next();
					 System.out.println("Enter CVV of Card");
					  CVV = sc.next();
				 }else if(mode.toString().equals("NET_BANKING")){
					 System.out.println("Enter UserId of Netbanking");
					 String netBankId=sc.next();
					 System.out.println("Enter Password of Netbanking");
					 String netBankPwd=sc.next(); 
				 }
				  String notificationMsg="";
				 if(mode == null) 
					 System.out.println("Invalid Payment Mode!!"); 
				 else { 
					 try {
						 	int transactionId = payment.makePayment(studentId, mode.toString(), Double.toString(fee),cardNo,Expiry,CVV);
							 if(transactionId > 0){
								 notificationMsg="Payment Succesfull with Amount of"+fee+"with Transaction Id of"+transactionId; 
							 }
							 notify.sendNotification(studentId, transactionId, notificationMsg);
					 }
					 catch (Exception e) {
						 logger.error(e.getMessage());
				 } 
				}
				 

			}
			else{
				return;
			}

		}
		System.out.println("Payment Successfull!!!\n");
	}

	private List<Course> viewRegisteredCourse(int studentId,int flag) {
		List<Course> registeredCourses = null;
		try {
			registeredCourses = semiRegiImpl.viewRegisteredCourses(studentId);
		} catch (SQLException e) {

			logger.error(e.getMessage());
		}
		if(flag==0){ 
		if (registeredCourses.isEmpty()) {
			System.out.println("You haven't registered for any course\n");
			return null;
		}
		
			System.out.println("-----------Registered Courses-----------");
			System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------","-----------", "----------"));
			System.out.println(String.format("|%-10s | %-10s | %-10s|", "COURSE CODE","COURSE NAME", "INSTRUCTOR"));
			System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------","-----------", "----------"));
			ProfessorImplService profImpl = new ProfessorImplService();
			for (Course obj : registeredCourses) {
	
				System.out.println(String.format("|%-11s | %-11s | %-10s|",obj.getCourseCode(), obj.getCourseName(),
						profImpl.getProfessorById(obj.getProfid())));
			}
		}

		return registeredCourses;
	}

	private List<Course> viewCourses(int studentId) {
		List<Course> coursesAvailable = null;
		List<Course> registeredCourses = viewRegisteredCourse(studentId,1);
		if(registeredCourses!=null && registeredCourses.size()==6){
			System.out.println("No Courses Available.You have Already Registered 6 Courses!!!!");
			return null;
		}
		try {
			coursesAvailable = semiRegiImpl.viewCourses(studentId);
		} catch (SQLException e) {
		}

		if (coursesAvailable==null && coursesAvailable.isEmpty()) {
			System.out.println("There are no Courses Available to Register");
			return null;
		}
		System.out.println("Courses Available to Register");
		System.out.println(String.format("%-10s | %-10s | %-10s","-----------", "-----------", "----------"));
		System.out.println(String.format("%-10s | %-10s | %-10s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
		System.out.println(String.format("%-10s | %-10s | %-10s","-----------", "-----------", "----------"));
		for (Course course : coursesAvailable) {
		System.out.println(String.format("%10s | %10s | %10s",course.getCourseCode(), course.getCourseName(),
					course.getProfid()));
		}

		return coursesAvailable;

	}

}
