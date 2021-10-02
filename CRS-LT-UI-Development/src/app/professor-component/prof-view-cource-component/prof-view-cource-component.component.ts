import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Professor } from 'src/model/professor';
import { ProfessorServiceService } from 'src/app/service/professor.service';
import { ViewCourse } from 'src/model/viewCourseModel';

@Component({
  selector: 'app-prof-view-cource-component',
  templateUrl: './prof-view-cource-component.component.html',
  styleUrls: ['./prof-view-cource-component.component.css']
})
export class ProfViewCourceComponentComponent implements OnInit {

  constructor(private router: Router,private service:ProfessorServiceService) { }
  myForm: FormGroup;
  courseDetails:ViewCourse[];
  prof:Professor[];
  profId:string="amit888";

  ngOnInit(): void {
    const prof1 = {
      profId:this.profId
    }
    
        this.service.viewCourseDetails(prof1).subscribe(data =>{
          this.courseDetails = data.body;
          // console.log(data.body)
        });
        this.myForm = new FormGroup({
          name: new FormControl(''),
          
        });
  }
  onSubmit(form: FormGroup){
  }

}
