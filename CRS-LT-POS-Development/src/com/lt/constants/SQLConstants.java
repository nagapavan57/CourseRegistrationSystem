package com.lt.constants;

public class SQLConstants {

	public static final String GET_COURSES="select * from course where profid=?";
	public static final String GET_STUDENTS_ENROLLED = "select course.coursecode,course.coursename,registeredcourses.studentid from course  inner join registeredcourses  on course.coursecode=registeredcourses.coursecode where course.profid=? order by course.coursecode;";
	public static final String ADD_GRADE="insert into grade(coursecode,coursename,grade,studentid) values(?,?,?,?)";
	public static final String GET_PROFFESSOR="select name from professor join role on professor.roleid=role.roleid join user on user.role=role.rolename where userid=?";
	
	public static final String VERIFY_CRED = "select userid,password from user where userid = ?";
	public static final String UPDATE_PASSWORD = "update user set password=? where userid = ? ";
	public static final String GET_ROLE = "select role from user where userid=?";
	
	public static final String ADD_USER_QUERY = "insert into user(userid, password, role) values (?, ?, ?)";
	public static final String ADD_STUDENT="insert into student (name,emailid,branchname,address,isapproved,roleid) values (?,?,?,?,?,?)";
	public static final String IS_APPROVED="select isapproved from student where studentid = ? ";
	public static final String GET_STUDENT_ID="select studentid from student join user where userid = ? ";
	
	public static final String REGISTER_COURSE="insert into registeredcourses (studentid,coursecode,coursename) values (?,?,?)";
	public static final String DECREMENT_COURSE_SEATS="update course set seats = seats-1 where coursecode = ? ";
	public static final String NUMBER_OF_REGISTERED_COURSES=" select studentid from registeredcourses where studentid = ? ";
	public static final String GET_SEATS = "select seats from course where coursecode = ?;";
	public static final String IS_REGISTERED=" select coursecode from registeredcourses where coursecode=? and studentid=? ";
	public static final String DROP_COURSE_QUERY = "delete from registeredcourses where coursecode = ? AND studentid = ?;";
	public static final String INCREMENT_SEAT_QUERY  = "update course set seats = seats + 1 where  coursecode = ?;";
	public static final String CALCULATE_FEES  = "select sum(coursefee) from course where coursecode in (select coursecode from registeredcourses where studentid = ?);";
	public static final String VIEW_GRADE = "select registeredcourses.coursecode,registeredcourses.coursename,grade.grade from registeredcourses inner join grade on registeredcourses.coursecode = grade.coursecode where registeredcourses.studentid = ?;";	
	public static final String VIEW_REGISTERED_COURSES=" select * from course inner join registeredcourses on course.coursecode = registeredcourses.coursecode where registeredcourses.studentid = ?";
	public static final String VIEW_AVAILABLE_COURSES=" select * from course where coursecode not in  (select coursecode  from registeredcourses where studentid = ?) and seats >=3 and seats <=10";
	public static final String GET_REGISTRATION_STATUS=" select isapproved from student where studentid = ? ";
	
	public static final String ADD_COURSES="insert into course(coursecode,coursename,profid, seats,coursefee,catalogid) values(?,?,?,?,?,?)";
	public static final String ADD_COURSES_TO_CATLOG = "insert into coursecatalog(coursecode,catalogid) value(?,?)";
	public static final String DEL_COURSES="delete from course where coursecode=?";
	public static final String DEL_COURSES_IN_CATALOG="delete from coursecatalog where coursecode=?";
	public static final String APPROVE_STUDENT="update student set isapproved=? where studentid=?";
	public static final String ASSIGN_COURSES="update course set profid=? where coursecode=?";
	public static final String ADD_PROFESSOR="insert into professor(department,name,subject,roleid)values(?,?,?,?)";
	public static final String VIEW_COURSES="select * from course";
	public static final String VIEW_PENDING_ADMISSION_QUERY = "select studentid,name, branchname,emailid,address from student where isapproved = 0";
	public static final String MAKE_PAYMENT = "insert into payment(studentid,mode,amount,cardno,expiry,cvv) values(?,?,?,?,?,?)";
	
	public static final String SEND_NOTIFICATION = "insert into notification(studentid,transactionid,notificationmsg) values(?,?,?)";
	//Getting role name from role table
	public static final String GET_ROLENAME = "select rolename from role where roleid=? ";
	
	public static final String VIEW_PROFESSOR_QUERY = "select user.userid, professor.name, professor.subject from professor join role on professor.roleid=role.roleid join user on user.role=role.rolename";
}
