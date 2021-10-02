import { Component, OnInit, ViewEncapsulation } from '@angular/core';
//import { NGXLogger } from 'ngx-logger';
import { StudentService } from '.././service/student.service';
//import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin.service';


@Component({
  selector: 'app-student-component',
  templateUrl: './student-component.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./student-component.component.css']
})
export class StudentComponentComponent implements OnInit {

  availableCourses: any[];
  registeredCourses: any[];
  gradeCard: any[];
  studentId:any;

  constructor(private _httpService: StudentService,private router:Router,private admin:AdminService) {
    //this.logger.debug('Inside the constructor of Student component');
  }

  ngOnInit(): void {
    this.getstudentId();
    this.viewAvailableCourses();
    this.viewRegisteredCourses();
    this.viewGradeCard();
  }
  getstudentId(){
    let userid =localStorage.getItem('userId');
    this.admin.getstudentId(userid).subscribe((res:any)=>{
      localStorage.setItem('studentId',res);
    });
  }

  viewAvailableCourses() {

    this._httpService.viewAvailableCourses()

      .subscribe((res: any[]) => {
        //console.log(res);
        //this.logger.debug(res);
        this.availableCourses = res;
      });
  }

  viewRegisteredCourses() {

    this._httpService.viewRegisteredCourses()

      .subscribe((res: any[]) => {
        //console.log(res);
        //this.logger.debug(res);
        this.registeredCourses = res;
      });
  }

  viewGradeCard() {

    this._httpService.viewGradeCard()

      .subscribe((res: any[]) => {
        //console.log(res);
        //this.logger.debug(res);
        this.gradeCard = res;
      });
  }

  registerCourse(courseCode, courseName) {
    this._httpService.registerCourse(courseCode, courseName)

      .subscribe((res: any) => {
        alert(res);
        window.location.reload();
        //this.logger.debug(res);
      });
  }

  dropCourse(courseCode) {
    this._httpService.dropCourse(courseCode)

      .subscribe((res: any) => {
        alert(res);
        window.location.reload();
        //this.logger.debug(res);
      });
  }
  someFn(){
    this.router.navigate(['']);
  }
}


