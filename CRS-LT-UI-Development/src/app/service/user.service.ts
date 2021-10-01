import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

const loginURL = 'http://localhost:8082/User/login';
const registerURL='http://localhost:8082/User/studentRegistration';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { 

  }

  checkLogin(data){
    return this.http.post(loginURL, data);
  }

  registerStudent(data){
    return this.http.post<any>(registerURL, data);
  }

}
