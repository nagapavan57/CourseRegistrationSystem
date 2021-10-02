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
  checkLogin(data){
    return this.http.post(loginURL, data,{headers:hdr, responseType: 'text'});
  }

  registerStudent(data){
    return this.http.post(registerURL,data,{headers:hdr, responseType: 'text'});
  }
  updatePassword(data){
    return this.http.put(updatePwdURL,data, {headers:hdr, responseType: 'text'});
  }

}
