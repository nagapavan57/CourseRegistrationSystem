package com.lt.business;

import org.apache.log4j.Logger;

import com.lt.bean.Student;
import com.lt.constants.Role;
import com.lt.dao.StudentDaoImpl;
import com.lt.exception.StudentNotRegisteredException;

public class StudentImplService implements StudentInterface {
	StudentDaoImpl studImpl = StudentDaoImpl.getInstance();
	private static Logger logger = Logger.getLogger(StudentImplService.class);

	public int register(String userId, String password, String name,
			String emailId, String branchName, String Address)
			throws StudentNotRegisteredException {
		int studId = 0;
		try {
			Student student = new Student(userId, password, Role.STUDENT, name,emailId, branchName, Address, false);
			studId = studImpl.registerStudent(student);
		} catch (StudentNotRegisteredException ex) {
			logger.error(ex.getMessage());
		}
		return studId;
	}

	@Override
	public int getStudentId(String userId) {
		return studImpl.getStudentId(userId);
	}

	@Override
	public boolean isApproved(int studentId) {

		return studImpl.isApproved(studentId);
	}

}
