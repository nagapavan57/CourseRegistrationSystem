import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EnrolledStudent } from 'src/model/enrolledStudentModel';

import { Professor } from 'src/model/professor';
import { ViewCourse } from 'src/model//viewCourseModel';
import { AddGrade } from 'src/model//addGrade';


type EntityResponseType = HttpResponse<EnrolledStudent[]>;
type EntityResponseType2 = HttpResponse<ViewCourse[]>;

const hdr =new HttpHeaders()

.set('content-type','application/json');

@Injectable({
  providedIn: 'root'
})
export class ProfessorServiceService {

  constructor(private http:HttpClient) { }


/**
 * 
 * @param id Professor Id in Json format
 * @returns list of students enrolled for the course he is teaching
 */
 getEnrolledStudents(id){
   return this.http.post("http://localhost:8082/Professor/viewEnrolledStudents", id);
  }
  /**
   * 
   * @param id Professor Id in Json format
   * @returns View Courses he is register to teach
   */
 viewCourseDetails(id){
   return this.http.post("http://localhost:8082/Professor/viewRegisteredCourses", id);
 }
 /**
  * 
  * @param addGrade Grade Object in json format
  * @returns Custom Response Message
  */
 addStudentGrade(addGrade){
   return this.http.post("http://localhost:8082/Professor/addGrade",addGrade,{headers:hdr, responseType: 'text'});
 }
}
