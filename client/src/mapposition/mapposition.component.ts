import { Component, Output, EventEmitter, Input } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-mapposition',
  templateUrl: './mapposition.component.html',
  styleUrls: ['./mapposition.component.sass']
})
export class MappositionComponent {

  constructor(private http: HttpClient) {}

  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;

  @Output() notify = new EventEmitter();

  onSubmit(event: Event) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));

    this.http.post('YOUR_BACKEND_ENDPOINT', data).subscribe(
      response => console.log('Success:', response),
      error => console.error('Error:', error)
    );
  }
}
