import { Component, OnInit} from '@angular/core';
import * as $ from 'jquery';
import { TweenMax } from 'gsap'
import { Sine } from 'gsap/all';
import { User } from 'src/model/user';
import { Student } from 'src/model/student';
import{UserService} from 'src/app/service/user.service';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin.service';
import { NGXLogger } from 'ngx-logger';



@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  
  userObj= new User('','');
  studentObj=new Student('','','','','','',0);
  submitted = true;
  updated=true;
  message:any;
  role:any;

  constructor(private user:UserService,private _httpService:UserService
    ,private router:Router,private admin:AdminService,private logger:NGXLogger) { }
  
  ngOnInit(): void {


   // this.document.body.classList.add('bodybg-color');
    $('#login-button').click(function () {
      $('#login-button').fadeOut("slow", function () {
        $("#container").fadeIn();
        TweenMax.from("#container", .4, { scale: 0, ease: Sine.easeInOut });
        TweenMax.to("#container", .4, { scale: 1, ease: Sine.easeInOut });
      });
    });

    $(".close-btn").click(function () {
      TweenMax.from("#container", .4, { scale: 1, ease: Sine.easeInOut });
      TweenMax.to("#container", .4, { left: "0px", scale: 0, ease: Sine.easeInOut });
      $("#container, #signup-container,#update-container").fadeOut(800, function () {
        $("#login-button").fadeIn(800);
      });
    });
    $(".orange-btn").click(function(){
      $("#signup-container,#update-container").fadeOut(600,function(){
        $("#container").fadeIn(800);
      });
    });

    $('#signup').click(function () {
      $("#container").fadeOut(function () {
        $("#signup-container").fadeIn();
      });
    });
    $('#update').click(function () {
      $("#container").fadeOut(function () {
        $("#update-container").fadeIn();
      });
    });
  }


  /**
   * It will check Userid,pwd and redirect to respective dashboard
   */
  checkLogin(){
    const data={
      userId:this.userObj.userId,
      password:this.userObj.password,
     
    }
    localStorage.setItem('userId',this.userObj.userId);
    
    
    this.user.checkLogin(data).subscribe((res:any)=>{
      this.logger.info(res);
      this.role=res;
      this.submitted=false;
      this.logger.info(this.role);
      if(this.role==="ADMIN"){
        this.router.navigate(['dashboardAdmin']);     
      }
      if(this.role==="STUDENT"){
      this.router.navigate(['dashboardStudent']);
      }
      if(this.role==="PROFESSOR"){
        this.router.navigate(['dashboardProfessor']);
       }
    })
    
  }

  /**
   * update Password of User
   */
  updatePassword(){
    const data={
      userId:this.userObj.userId,
      newPassword:this.userObj.password,
    }
    this._httpService.updatePassword(data).subscribe((res:any)=>{
      this.logger.info(res);
      this.updated=false;
      this.message=res;
      if(res==="Updated"){
        this.message="Password Updated Succesfully for user";
      }
    })
  }
/**
 * Signup method for Student
 */
  register(){
    const data={
      userId:this.studentObj.userId,
      password:this.studentObj.password,
      name:this.studentObj.name,
      emailId:this.studentObj.emailId,
      branchName:this.studentObj.branchName,
      Address:this.studentObj.Address
    }

    this._httpService.registerStudent(data).subscribe((messg:any)=>{
          this.submitted=false;
          this.logger.info("Response::"+messg);
          this.message=messg;
    })
    this.router.navigate(['/']);
  }


}