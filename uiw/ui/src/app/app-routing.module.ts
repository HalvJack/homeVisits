import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorFormComponent} from "../doctor-form/doctor-form.component";
import {AppComponent} from "./app.component";

const routes: Routes = [
  {path: 'app-doctor-form', component: DoctorFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
