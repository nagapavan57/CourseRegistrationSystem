package com.lt.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lt.bean.Course;
import com.lt.bean.StudentsEnrolled;
import com.lt.dao.ProfessorDaoImpl;
import com.lt.exception.GradeNotAddedException;

public class ProfessorImplService implements ProfessorInterface {
	ProfessorDaoImpl professorDAO = ProfessorDaoImpl.getInstance();
	private static Logger logger = Logger.getLogger(ProfessorImplService.class);

	public boolean addGrade(int studentId, String courseCode, String courseName, String grade)
			throws GradeNotAddedException {

		try {
			return professorDAO.addGrade(studentId, courseCode, courseName, grade);
		} catch (GradeNotAddedException e) {
			logger.error(e.getMessage(studentId));
		}
		return false;
	}

	public List<StudentsEnrolled> viewEnrolledStudents(String profName) throws SQLException {
		List<StudentsEnrolled> studentList = new ArrayList<StudentsEnrolled>();

		try {
			studentList = professorDAO.getEnrolledStudents(profName);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return studentList;
	}

	@Override
	public List<Course> viewRegisteredCourses(String profName) throws SQLException {

		List<Course> coursesTeaching = new ArrayList<Course>();
		try {
			coursesTeaching = professorDAO.getCoursesByProfessor(profName);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return coursesTeaching;

	}

	@Override
	public String getProfessorById(String profId) {
		return professorDAO.getProfessorById(profId);
	}

}
