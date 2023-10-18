import {Component, OnInit} from '@angular/core';
import {MainService} from "../../services/main/main.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.sass']
})
export class MainComponent implements OnInit {
  data: any;

  constructor(private service: MainService) {
  }

  ngOnInit() {
    this.service.getData().subscribe((data) => {
      this.data = data;
    });
  }
}
