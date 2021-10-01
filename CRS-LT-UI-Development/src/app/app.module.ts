import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
//import { AppComponent } from './app.component';
import { StudentComponentComponent } from './student-component/student-component.component';
import { UserComponentComponent } from './user-component/user-component.component';
import { ProfessorComponentComponent } from './professor-component/professor-component.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    //AppComponent,
    StudentComponentComponent,
    UserComponentComponent,
    ProfessorComponentComponent,
    AdminComponentComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [UserComponentComponent]
})
export class AppModule { }
