import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponentComponent } from './user-component/user-component.component';
import { StudentComponentComponent } from './student-component/student-component.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import { ProfessorComponentComponent } from './professor-component/professor-component.component';

const routes: Routes = [

  //{ path : '', component:  UserComponentComponent },
  { path : 'dashboardStudent',component: StudentComponentComponent},
  { path : 'dashboardAdmin',component: AdminComponentComponent},
  { path : 'dashboardProfessor',component: ProfessorComponentComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
