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



 getEnrolledStudents(id):Observable<EntityResponseType>{
   return this.http.post<EnrolledStudent[]>("http://localhost:8082/Professor/viewEnrolledStudents", id, {observe: 'response'});
}

 viewCourseDetails(id):Observable<EntityResponseType2>{
   return this.http.post<ViewCourse[]>("http://localhost:8082/Professor/viewRegisteredCourses", id, {observe: 'response'});
 }

 addStudentGrade(addGrade:AddGrade){
   return this.http.post("http://localhost:8082/Professor/addGrade",addGrade,{headers:hdr, responseType: 'text'});
 }
}
