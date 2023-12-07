import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorFormComponent} from "../doctor-form/doctor-form.component";
import { MainComponent} from "../main/main.component";
import {MaptileComponent} from "../maptile/maptile.component";
import {MappositionComponent} from "../mapposition/mapposition.component";

const routes: Routes = [
  {path: 'doctors', component: MainComponent},
  {path: 'addDoctor', component: DoctorFormComponent},
  {path: 'maptiles', component: MaptileComponent},
  {path: 'location', component: MappositionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
