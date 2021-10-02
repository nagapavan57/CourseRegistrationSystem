import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AdminService } from './admin.service';
import { Admin } from 'src/model/admin';

const hdr = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})

export class StudentService {

  constructor(private http: HttpClient,private admin:AdminService) { }
 
  studentId=localStorage.getItem('studentId');
  
  viewAvailableCourses() {
    return this.http.get('http://localhost:8082/Student/viewAvailableCourses/'+this.studentId);
    
  }

  viewRegisteredCourses() {
    return this.http.get('http://localhost:8082/Student/viewRegisteredCourses/'+this.studentId);
  }

  viewGradeCard() {
    return this.http.get('http://localhost:8082/Student/viewGradeCard/'+this.studentId);
  }

  registerCourse(courseCode, courseName) {
    const body = {
      courseCode: courseCode,
      courseName: courseName,
      studentId: this.studentId
    };
    return this.http.post('http://localhost:8082/Student/registerCourse', body, { headers: hdr, responseType: 'text' });
  }

  dropCourse(courseCode) {
    const body = {
      courseCode: courseCode,
      studentId: this.studentId
    };
    return this.http.post('http://localhost:8082/Student/dropCourse', body, { headers: hdr, responseType: 'text' });
  }
}






