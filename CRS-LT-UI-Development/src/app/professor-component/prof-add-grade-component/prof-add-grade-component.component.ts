import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { Professor } from 'src/model/professor';
import { Student } from 'src/model/student';
import { AddGrade } from 'src/model/addGrade';
import { EnrolledStudent } from 'src/model/enrolledStudentModel';
import { ProfessorServiceService } from 'src/app/service/professor.service';

@Component({
  selector: 'app-prof-add-grade-component',
  templateUrl: './prof-add-grade-component.component.html',
  styleUrls: ['./prof-add-grade-component.component.css']
})
export class ProfAddGradeComponentComponent implements OnInit {
 
  constructor(private router: Router,private service:ProfessorServiceService) { }
  myForm: FormGroup;
  studentDetails:EnrolledStudent[];
  prof:Professor[];
  addGrade:AddGrade[];
  profId:string="amit888";
  i = '';
  studentDetails2:EnrolledStudent;
  ngOnInit(): void {
    const prof1 = {
      profId:this.profId
    }
    this.service.getEnrolledStudents(prof1).subscribe(data =>{
      this.studentDetails = data.body;
      // console.log(data.body)
    });
    {
    this.myForm = new FormGroup({
      // code: new FormControl(''),
      // course : new FormControl(''),
      // stdid : new FormControl(''),
      grade: new FormControl('')
      });
    }
      
  // Add Grade
      
      

  }
  method(courseCode,courseName,studentId){
     this.studentDetails2 = {
      courseCode:courseCode,
      courseName:courseName,
      studentId:studentId
     }
  }
  onSubmit(form: FormGroup){
    let addGrade:AddGrade = {
      courseCode:this.studentDetails2.courseCode,
      courseName:this.studentDetails2.courseName,
      grade:form.value.grade,
      studentId:this.studentDetails2.studentId
   }
   
  console.log("Adding Grade");
         this.service.addStudentGrade(addGrade).subscribe(data =>{
            console.log(data);
            console.log("grade Added!!");
         });
  
  }
  
}