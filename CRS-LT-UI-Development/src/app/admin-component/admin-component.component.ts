import { Component, OnInit } from '@angular/core';
import Swal  from "sweetalert2";
import * as $ from 'jquery';

@Component({
  selector: 'app-admin-component',
  templateUrl: './admin-component.component.html',
  styleUrls: ['./admin-component.component.css']
})
export class AdminComponentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    function registerCourseMessage() {
      Swal.fire("Registered!", "You have successfully registered for the course!", "success");
    }

    function dropCourseMessage() {
      Swal.fire("Dropped!", "Course droppped successfully!", "success");
    }

    $('#btn').click(function () {
      $('#modelWindow').modal('show');
    });
  }

}
