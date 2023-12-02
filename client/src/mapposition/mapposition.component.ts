import { Component, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-mapposition',
  templateUrl: './mapposition.component.html',
  styleUrls: ['./mapposition.component.sass']
})
export class MappositionComponent {

  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;

  @Output() notify = new EventEmitter();

}
