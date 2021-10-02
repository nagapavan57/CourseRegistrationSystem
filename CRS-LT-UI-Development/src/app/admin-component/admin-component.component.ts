import { Component, OnInit } from '@angular/core';
import Swal  from "sweetalert2";
import * as $ from 'jquery';
import { Course } from 'src/model/course';
import { Professor } from 'src/model/professor';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';

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

  constructor(private admin:AdminService,private router:Router) { }

  ngOnInit(): void {
    this.getCourses();
    this.pendingAdmissions();
    
  }
  pendingAdmissions(){
    this.admin.viewPendingAdmissions().subscribe((res:any[])=>{
      this.pendingAdmissionList=res;
      console.log(res);
    })
  }

  getCourses(){
    this.admin.getCourses().subscribe((res:any[])=>{
      this.courseList=res;
      console.log(res);
    })
  }
  deleteCourse(courseCode){

    this.admin.deleteCourse(courseCode).subscribe((res:any)=>{
      this.message=res;
      console.log(res);
      window.location.reload();
    })
  }

  addCourse(){
    const data={
      courseCode:this.course.courseCode,
      courseName:this.course.courseName,
      seats:this.course.seats,
      fee:this.course.fee
    }
      this.admin.addCourse(data).subscribe((res:any)=>{
        this.message=res;
        console.log(res);
        window.location.reload();
      })
  }
  approveStudent(studentId){
    this.admin.approveStudent(studentId).subscribe((res:any)=>{
      this.message=res;
      console.log(res);
      window.location.reload();
    })
    
  }
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
      console.log(res);
      window.location.reload();
    })
  }

  assignCourse(){
    const data={
      courseCode:this.course.courseCode,
      professorId:this.professor.userId
    }

    this.admin.assignCourse(data).subscribe((res:any)=>{
      this.message=res;
      console.log(res);
      window.location.reload();
    })
  }
  someFn(){
    this.router.navigate(['']);
  }
}
