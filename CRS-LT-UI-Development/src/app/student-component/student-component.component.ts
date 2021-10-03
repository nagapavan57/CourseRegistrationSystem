import { Component, OnInit, ViewEncapsulation } from '@angular/core';
//import { NGXLogger } from 'ngx-logger';
import { StudentService } from '.././service/student.service';
//import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin.service';
import { NGXLogger } from 'ngx-logger';


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

  constructor(private _httpService: StudentService,private router:Router,private admin:AdminService,
    private logger:NGXLogger) {
    //this.logger.debug('Inside the constructor of Student component');
  }

  ngOnInit(): void {
    this.getstudentId();
    this.viewAvailableCourses();
    this.viewRegisteredCourses();
    this.viewGradeCard();
  }
  /**
   * Getting Student Id by UserId
   */
  getstudentId(){
    let userid =localStorage.getItem('userId');
    this.admin.getstudentId(userid).subscribe((res:any)=>{
      localStorage.setItem('studentId',res);
    });
    window.location.reload();
  }
  /**
   * List Of Courses Available to Register
   */
  viewAvailableCourses() {

    this._httpService.viewAvailableCourses()

      .subscribe((res: any[]) => {
        //console.log(res);
        this.logger.info(res);
        this.availableCourses = res;
      });
  }
 /**
 * Get the List of Registered Courses of Student  
 */
  viewRegisteredCourses() {

    this._httpService.viewRegisteredCourses()

      .subscribe((res: any[]) => {
        //console.log(res);
        this.logger.info(res);
        this.registeredCourses = res;
      });
  }
  /**
   * List the Grade Card
   */
  viewGradeCard() {

    this._httpService.viewGradeCard()

      .subscribe((res: any[]) => {
        //console.log(res);
        this.logger.info(res);
        this.gradeCard = res;
      });
  }
/**
 * 
 * @param courseCode Course code in which students wants to register
 * @param courseName Course name in which students wants to register
 */
  registerCourse(courseCode, courseName) {
    this._httpService.registerCourse(courseCode, courseName)

      .subscribe((res: any) => {
        window.location.reload();
        this.logger.info(res);
      });
  }
  /**
   * 
   * @param courseCode COurse code in which student wants to drop
   */
  dropCourse(courseCode) {
    this._httpService.dropCourse(courseCode)

      .subscribe((res: any) => {
        window.location.reload();
        this.logger.info(res);
      });
  }
  /**
   * Logout Redirecting method
   */
  someFn(){
    this.router.navigate(['']);
  }
}


