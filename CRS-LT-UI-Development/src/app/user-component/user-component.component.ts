import { Component, OnInit} from '@angular/core';
import * as $ from 'jquery';
import { TweenMax } from 'gsap'
import { Sine } from 'gsap/all';
import { User } from 'src/model/user';
import { Student } from 'src/model/student';
import{UserService} from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  
  userObj= new User('','');
  studentObj=new Student('','','','','','');
  submitted = true;
  message:any[];
  role:any;
  constructor(private user:UserService,private _httpService:UserService
    ,private router:Router) { }
  
  ngOnInit(): void {

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
      $("#container, #signup-container").fadeOut(800, function () {
        $("#login-button").fadeIn(800);
      });
    });


    $('#signup').click(function () {
      $("#container").fadeOut(function () {
        $("#signup-container").fadeIn();
      });
    });
  }

  checkLogin(){
    const data={
      userId:this.userObj.userId,
      password:this.userObj.password,
     
    }
    this.user.checkLogin(data).subscribe((res:any)=>{
      console.log(res);
      this.role=res;
      console.log(this.role);
    });
    
  }
  login(){
   
    if(this.role==="ADMIN"){
      this.router.navigate(['dashboardAdmin']);     
    }
    if(this.role==="STUDENT"){
        this.router.navigate(['dashboardStudent']);
    }
    if(this.role==="PROFESSOR"){
      this.router.navigate(['dashboardProfessor']);
     }
    
  }

  register(){
    const data={
      userId:this.studentObj.userId,
      password:this.studentObj.password,
      name:this.studentObj.name,
      emailId:this.studentObj.emailId,
      branchName:this.studentObj.branchName,
      Address:this.studentObj.Address
    }

    this._httpService.registerStudent(data).subscribe((messg:any)=>
          {
          this.submitted=true;
          console.log("Response::"+messg);
          this.message=messg;
          },
      error => {
        console.log(error);
    });
    //console.log(this.message);
  }


}