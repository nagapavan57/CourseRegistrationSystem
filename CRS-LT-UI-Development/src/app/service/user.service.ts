import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

const loginURL = 'http://localhost:8082/User/login';
const registerURL='http://localhost:8082/User/studentRegistration';
const updatePwdURL="http://localhost:8082/User/updatePassword";


const hdr =new HttpHeaders()
.set('content-type','application/json');




@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http:HttpClient) { 

  }
  /**
   * 
   * @param data userid and pwassword in json format
   * @returns Role of User
   */
  checkLogin(data){
    return this.http.post(loginURL, data,{headers:hdr, responseType: 'text'});
  }

  /**
   * 
   * @param data Student data in json format
   * @returns Custom Response Message
   */
  registerStudent(data){
    return this.http.post(registerURL,data,{headers:hdr, responseType: 'text'});
  }

  /**
   * 
   * @param data userid and newpwssword in json format
   * @returns Custom Response Message
   */
  updatePassword(data){
    return this.http.put(updatePwdURL,data, {headers:hdr, responseType: 'text'});
  }

}
