import {Component, OnInit} from '@angular/core';
import {MainService} from "./main.service";
import {Doctor} from "../doctor-form/doctor";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.sass']
})
export class MainComponent implements OnInit {
  doctors!: Doctor[];

  constructor(private service: MainService) {
  }

  ngOnInit() {

  }
}
