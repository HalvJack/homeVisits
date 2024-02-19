import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorFormComponent} from "../doctor-form/doctor-form.component";
import {HomeComponent} from "../home/home.component";
import {JsmapComponent} from "../jsmap/jsmap.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'app-doctor-form', component: DoctorFormComponent},
  {path: 'app-jsmap', component: JsmapComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
