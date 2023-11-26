import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {DoctorFormComponent} from './components/doctor-form/doctor-form.component';
import {MainService} from "./services/main/main.service";
import {MainComponent} from "./components/main/main.component";
import { MaptileComponent } from './maptile/maptile.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorFormComponent,
    MainComponent,
    MaptileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [MainService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
