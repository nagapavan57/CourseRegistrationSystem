import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponentComponent } from './user-component/user-component.component';
import { StudentComponentComponent } from './student-component/student-component.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import { ProfessorComponentComponent } from './professor-component/professor-component.component';
import {ProfViewCourceComponentComponent} from './professor-component/prof-view-cource-component/prof-view-cource-component.component';
import {ProfEnrolldStudentsComponentComponent} from './professor-component/prof-enrolld-students-component/prof-enrolld-students-component.component'
import {ProfAddGradeComponentComponent} from './professor-component/prof-add-grade-component/prof-add-grade-component.component'
const routes: Routes = [

  { path : '', component:  UserComponentComponent},
  { path : 'dashboardStudent',component: StudentComponentComponent},
  { path : 'dashboardAdmin',component: AdminComponentComponent},
  { path : 'dashboardProfessor',component: ProfessorComponentComponent},
  {path:'app-professor-component',component:ProfessorComponentComponent},
  {path:'app-prof-view-cource-component',component:ProfViewCourceComponentComponent},
  {path:'app-prof-enrolld-students-component',component:ProfEnrolldStudentsComponentComponent},
  {path:'app-prof-add-grade-component',component:ProfAddGradeComponentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
