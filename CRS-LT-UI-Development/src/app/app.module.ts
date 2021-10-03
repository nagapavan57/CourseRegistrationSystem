import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms'; 
import { BrowserModule } from '@angular/platform-browser';
import { MatTabsModule } from '@angular/material/tabs';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { StudentComponentComponent } from './student-component/student-component.component';
import { UserComponentComponent } from './user-component/user-component.component';
import { ProfessorComponentComponent } from './professor-component/professor-component.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfViewCourceComponentComponent } from '../app/professor-component/prof-view-cource-component/prof-view-cource-component.component';
import { ProfEnrolldStudentsComponentComponent } from '../app/professor-component/prof-enrolld-students-component/prof-enrolld-students-component.component';
import { ProfAddGradeComponentComponent } from '../app/professor-component/prof-add-grade-component/prof-add-grade-component.component';
import { CommonPipe } from './pipe/common.pipe';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponentComponent,
    UserComponentComponent,
    ProfessorComponentComponent,
    AdminComponentComponent,
    ProfViewCourceComponentComponent,
    ProfEnrolldStudentsComponentComponent,
    ProfAddGradeComponentComponent,
    CommonPipe

  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MatTabsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    LoggerModule.forRoot({serverLoggingUrl: '/users/logs', level: NgxLoggerLevel.INFO,serverLogLevel: NgxLoggerLevel.ERROR})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
