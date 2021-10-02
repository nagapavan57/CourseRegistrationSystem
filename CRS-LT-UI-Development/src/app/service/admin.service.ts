import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const hdr = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  private userid:string=localStorage.getItem('userId');
  constructor(private http: HttpClient) { }

  getstudentId(userId){
    console.log(this.http.get('http://localhost:8082/Student/getStudentId/'+this.userid,{ headers: hdr, responseType: 'text' }));
    return this.http.get('http://localhost:8082/Student/getStudentId/'+this.userid,{ headers: hdr, responseType: 'text' })
  }  
  
  getCourses() {
    return this.http.get('http://localhost:8082/admin/viewCoursesInCatalogue');
    
  }
  addCourse(data) {
    return this.http.post('http://localhost:8082/admin/addCourse', data, { headers: hdr, responseType: 'text' });
  }

  deleteCourse(courseCode){
    return this.http.delete('http://localhost:8082/admin/deleteCourse/'+courseCode,{ headers: hdr, responseType: 'text' })
  }

  viewPendingAdmissions(){
    return this.http.get('http://localhost:8082/admin/viewPendingAddmission'); 
  }

  approveStudent(studentId){
    return this.http.put('http://localhost:8082/admin/approveStudent',studentId,{headers: hdr, responseType: 'text' });
  }
  addProfessor(data){
    return this.http.post('http://localhost:8082/admin/addProfessor', data, {headers: hdr, responseType: 'text' });
  }
  assignCourse(data){
    return this.http.put('http://localhost:8082/admin/assignProfessor',data,{headers: hdr, responseType: 'text' });
  }

}






