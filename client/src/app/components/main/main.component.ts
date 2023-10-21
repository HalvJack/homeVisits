import {Component, OnInit} from '@angular/core';
import {MainService} from "../../services/main/main.service";
import {Doctor} from "../../doctor";

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
    this.service.findAllDoctors().subscribe((data) => {
      this.doctors = data;
    });
  }
}
