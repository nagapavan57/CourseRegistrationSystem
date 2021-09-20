package com.lt.client;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.bean.Student;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplService;
import com.lt.constants.Role;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

public class CRSApplication {
	UserImplService userInterface = new UserImplService();
	StudentImplService studInterfce = new StudentImplService();
	private static Logger logger = Logger.getLogger(CRSApplication.class);
	
	public static void main(String[] args) throws SQLException, StudentNotFoundException {
		
		CRSApplication crsApplication = new CRSApplication();
		
			Scanner crsInput = new Scanner(System.in);
			int input;
			menu();
			input=crsInput.nextInt();
		while(input!=4){			
			switch (input) {
			case 1:
				crsApplication.userLogin();
				break;
			case 2:
				crsApplication.registerStudent();
				break;
			case 3:
				crsApplication.updatePassword();
				break;
			default:
				System.out.println("Invalid Input!!!");
				break;
			}
			menu();
			input=crsInput.nextInt();
		}
		
		
		
	}
	public void userLogin(){
		
		Scanner loginInput =  new Scanner(System.in);
		System.out.println("--------CRS Login--------");
		System.out.println("Please Enter UserId");
		String userId= loginInput.next();
		System.out.println("Please Enter Password");
		String password = loginInput.next();
		try{
			String userRole = userInterface.getRoleById(userId);
			boolean login = userInterface.verifyCredentials(userId, password);
			if(!login){
				System.out.println("UserId or Password Incorrect. Please contact Admin!!");
			}
			if(login){
				switch(userRole)
				{
				case "ADMIN":
					
					System.out.println("Logged in Successfully!!\n");
					CRSAdminMenu adminMenu=new CRSAdminMenu();
					adminMenu.mainMenu();
					break;
				case "PROFESSOR":
					
					System.out.println("Logged in Successfully!!\n");
					CRSProfessorMenu professorMenu=new CRSProfessorMenu();
					professorMenu.mainMenu(userId);
					
					break;
				case "STUDENT":
					
					int studentId = studInterfce.getStudentId(userId);
					boolean isApproved=studInterfce.isApproved(studentId);
					if(isApproved)
					{
						System.out.println("Logged in Successfully!!\n");
						CRSStudentMenu studentMenu=new CRSStudentMenu();
						studentMenu.mainMenu(studentId);
						
					}
					else
					{
						System.out.println("Registration Not Approved.Please Contact Admin!!");
					}
					break;
				}
			}
		}catch(UserNotFoundException e){
			logger.error(e.getMessage());
		}
	}
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,password,name,emailId,address,branchName,gender;
		
		int studentId;
		try
		{
			//input all the student details
			System.out.println("Please Enter userId");
			userId=sc.next();
			System.out.println("Please Enter password");
			password=sc.next();
			System.out.println("Please Enter your name");
			name=sc.next();
			System.out.println("Please Enter Email");
			emailId=sc.next();
			System.out.println("Please Enter branchName");
			branchName=sc.next();
			System.out.println("Please Enter your Address");
			address=sc.next();
			
			Student newStudent=new Student(userId, password,Role.STUDENT,name,emailId, branchName,address,false);
			int newStudId=studInterfce.register(userId, password,name,emailId, branchName, address);
			//notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null,0);
			System.out.println("Your Registration is Succesfull "+name+"!!");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}
	public void updatePassword()
	{
		Scanner sc=new Scanner(System.in);
		String userId,newPassword;
		try
		{
			System.out.println("------------------Update Password--------------------");
			System.out.println("userId");
			userId=sc.next();
			System.out.println("New Password:");
			newPassword=sc.next();
			boolean isUpdated=userInterface.updatePassword(userId, newPassword);
			if(isUpdated)
				System.out.println("Password updated successfully!");

			else
				System.out.println("Something went wrong, please try again!");
		}
		catch(Exception ex)
		{
			logger.error("Error Occured "+ex.getMessage());
		}
	}
	public static void menu(){
		System.out.println("Welcome to CRS Application");
		System.out.println("1. Login");
		System.out.println("2. Signup");
		System.out.println("3. Update Password");
		System.out.println("4. Exit");
		System.out.println("Enter your choice");
	}
}
