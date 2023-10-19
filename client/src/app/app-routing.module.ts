import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Importuj komponenty, które będą używane w trasach
import { MainComponent} from "./components/main/main.component";

const routes: Routes = [
  { path: 'doctor/1', component: MainComponent }, // Strona główna
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
