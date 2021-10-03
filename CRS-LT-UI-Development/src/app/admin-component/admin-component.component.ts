import { Component, OnInit } from '@angular/core';
import Swal  from "sweetalert2";
import * as $ from 'jquery';
import { Course } from 'src/model/course';
import { Professor } from 'src/model/professor';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-admin-component',
  templateUrl: './admin-component.component.html',
  styleUrls: ['./admin-component.component.css']
})
export class AdminComponentComponent implements OnInit {

  course=new Course('','','','');
  professor= new Professor('','','','','');

  message:string;
  courseList:any[];
  pendingAdmissionList:any[];

  constructor(private admin:AdminService,private router:Router,private logger:NGXLogger) { }

  ngOnInit(): void {
    this.getCourses();
    this.pendingAdmissions();
    
  }
  /**
   * It will Give List of Pending Admissions
   */
  pendingAdmissions(){
    this.admin.viewPendingAdmissions().subscribe((res:any[])=>{
      this.pendingAdmissionList=res;
      this.logger.info(res);
    })
  }
  /**
   * It will give List of Courses in catalog
   */
  getCourses(){
    this.admin.getCourses().subscribe((res:any[])=>{
      this.courseList=res;
      this.logger.info(res);
    })
  }
  /**
   * 
   * @param courseCode Coursecode which needs to be deleted
   */
  deleteCourse(courseCode){

    this.admin.deleteCourse(courseCode).subscribe((res:any)=>{
      this.message=res;
      this.logger.info(res);
      window.location.reload();
    })
  }
  /**
   * It will add course into Catalog
   */
  addCourse(){
    const data={
      courseCode:this.course.courseCode,
      courseName:this.course.courseName,
      seats:this.course.seats,
      fee:this.course.fee
    }
      this.admin.addCourse(data).subscribe((res:any)=>{
        this.message=res;
        this.logger.info(res);
        window.location.reload();
      })
  }
  /**
   * 
   * @param studentId It Will Approve Particular Student
   */
  approveStudent(studentId){
    this.admin.approveStudent(studentId).subscribe((res:any)=>{
      this.message=res;
      this.logger.info(res);
      window.location.reload();
    })
    
  }
  /**
   * Adding Professor into System
   */
  addProfessor(){
    const data={
      userId:this.professor.userId,
      password:this.professor.password,
      name:this.professor.name,
      subject:this.professor.subject,
      department:this.professor.department
    }
    this.admin.addProfessor(data).subscribe((res:any)=>{
      this.message=res;
      this.logger.info(res);
      window.location.reload();
    })
  }
/**
 * Assigning Course to Particular Professor
 */
  assignCourse(){
    const data={
      courseCode:this.course.courseCode,
      professorId:this.professor.userId
    }

    this.admin.assignCourse(data).subscribe((res:any)=>{
      this.message=res;
      this.logger.info(res);
      window.location.reload();
    })
  }
  /**
   * Routing Function for Logout
   */
  someFn(){
    this.router.navigate(['']);
  }
}
