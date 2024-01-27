import { Component, Output, EventEmitter, Input } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "./location.service";
import {Location} from "./location"
import {Importance} from "./importance";

@Component({
  selector: 'app-mapposition',
  templateUrl: './mapposition.component.html',
  styleUrls: ['./mapposition.component.sass']
})
export class MappositionComponent {

  location: Location;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private locationService: LocationService) {
    this.location = new Location(1,1);
  }

  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;

  @Output() notify = new EventEmitter();

  onSubmit(event: Event) {
    this.locationService.saveLocation(this.location).subscribe(result => this.goToLocation());
  }

  private goToLocation(){
    this.router.navigate(['/location'])
  }
}
