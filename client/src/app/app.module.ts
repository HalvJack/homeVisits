import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppComponent} from './app.component';
import {DoctorFormComponent} from '../doctor-form/doctor-form.component';
import {MainService} from "../main/main.service";
import {MainComponent} from "../main/main.component";
import { MaptileComponent } from '../maptile/maptile.component';
import { JsmapComponent } from '../jsmap/jsmap.component';
import { MappositionComponent } from '../mapposition/mapposition.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from "@angular/material/card";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule, MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule, MatDatepickerToggle} from "@angular/material/datepicker";

@NgModule({
  declarations: [
    AppComponent,
    DoctorFormComponent,
    MainComponent,
    MaptileComponent,
    JsmapComponent,
    MappositionComponent
  ],
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [
    MainService,
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}},
    MatNativeDateModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
