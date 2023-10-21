import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorFormComponent} from "./components/doctor-form/doctor-form.component";
import { MainComponent} from "./components/main/main.component";

const routes: Routes = [
  {path: 'doctors', component: MainComponent},
  {path: 'addDoctor', component: DoctorFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
