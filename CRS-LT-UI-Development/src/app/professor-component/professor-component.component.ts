import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl} from '@angular/forms';
import { Professor } from 'src/model/professor';
import {ProfessorServiceService} from '../service/professor.service'
//import { ViewCourse } from './viewCourseModel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
//import { EnrolledStudent } from './enrolledStudentModel';

@Component({
  selector: 'app-professor-component',
  templateUrl: './professor-component.component.html',
  styleUrls: ['./professor-component.component.css']
})
export class ProfessorComponentComponent implements OnInit {

  constructor(private router: Router) { }
  

  ngOnInit(): void {
    
}
}



