import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorFormComponent} from "./components/doctor-form/doctor-form.component";
import { MainComponent} from "./components/main/main.component";
import {MaptileComponent} from "./maptile/maptile.component";

const routes: Routes = [
  {path: 'doctors', component: MainComponent},
  {path: 'addDoctor', component: DoctorFormComponent},
  {path: 'maptiles', component: MaptileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
