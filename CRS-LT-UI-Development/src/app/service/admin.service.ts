import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const hdr = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  private userid:string=localStorage.getItem('userId');
  constructor(private http: HttpClient) { }
  /**
   * 
   * @param userId
   * @returns studentId from given userId
   */
  getstudentId(userId){
    console.log(this.http.get('http://localhost:8082/Student/getStudentId/'+this.userid,{ headers: hdr, responseType: 'text' }));
    return this.http.get('http://localhost:8082/Student/getStudentId/'+this.userid,{ headers: hdr, responseType: 'text' })
  }  
  /**
   * 
   * @returns list of courses in catalog
   */
  getCourses() {
    return this.http.get('http://localhost:8082/admin/viewCoursesInCatalogue');
    
  }
  /**
   * 
   * @param data json data for adding course
   * @returns Custom Response Message
   */
  addCourse(data) {
    return this.http.post('http://localhost:8082/admin/addCourse', data, { headers: hdr, responseType: 'text' });
  }
  /**
   * 
   * @param courseCode coursecode which needs to be deleted from catalog
   * @returns Custom Response Message
   */
  deleteCourse(courseCode){
    return this.http.delete('http://localhost:8082/admin/deleteCourse/'+courseCode,{ headers: hdr, responseType: 'text' })
  }
  /**
   * 
   * @returns list of students who are pending for approval
   */
  viewPendingAdmissions(){
    return this.http.get('http://localhost:8082/admin/viewPendingAddmission'); 
  }
  /**
   * 
   * @param studentId studentid which needs to be approve
   * @returns Custom Response Message
   */
  approveStudent(studentId){
    return this.http.put('http://localhost:8082/admin/approveStudent',studentId,{headers: hdr, responseType: 'text' });
  }
  /**
   * 
   * @param data Professor Json
   * @returns Custom Response Message
   */
  addProfessor(data){
    return this.http.post('http://localhost:8082/admin/addProfessor', data, {headers: hdr, responseType: 'text' });
  }
  /**
   * 
   * @param data Course and Professor details in json format
   * @returns Custom Response Message
   */
  assignCourse(data){
    return this.http.put('http://localhost:8082/admin/assignProfessor',data,{headers: hdr, responseType: 'text' });
  }

}






