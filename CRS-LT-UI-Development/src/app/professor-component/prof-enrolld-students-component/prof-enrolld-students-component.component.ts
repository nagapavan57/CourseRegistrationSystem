import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Professor } from 'src/model/professor';
import { EnrolledStudent } from 'src/model/enrolledStudentModel';
import { ProfessorServiceService } from 'src/app/service/professor.service';

@Component({
  selector: 'app-prof-enrolld-students-component',
  templateUrl: './prof-enrolld-students-component.component.html',
  styleUrls: ['./prof-enrolld-students-component.component.css']
})
export class ProfEnrolldStudentsComponentComponent implements OnInit {

  constructor(private router: Router,private service:ProfessorServiceService) { }
  myForm: FormGroup;
  studentDetails:EnrolledStudent[];
  prof:Professor[];
  profId:string="amit888";

  ngOnInit(): void {
    const prof1 = {
      profId:this.profId
    }
    this.service.getEnrolledStudents(prof1).subscribe(data =>{
      this.studentDetails = data.body;
      // console.log(data.body)
    });
    this.myForm = new FormGroup({
      name: new FormControl(''),
      
    });
  }
  onSubmit(form: FormGroup){
  }

}
