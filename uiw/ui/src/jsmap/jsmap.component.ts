import {Component, ViewChild, ElementRef, Input, SimpleChanges, Output, EventEmitter} from '@angular/core';
import {GeolocationService} from "../app/geolocation.service";
import H from '@here/maps-api-for-javascript';
import onResize from 'simple-element-resize-detector';

@Component({
  selector: 'app-jsmap',
  templateUrl: './jsmap.component.html',
  styleUrls: ['./jsmap.component.sass']
})
export class JsmapComponent {
  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;
  private map?: H.Map;

  constructor(private geolocationService: GeolocationService) {
  }

  @ViewChild('map') mapDiv?: ElementRef;

  ngAfterViewInit(): void {
    if (!this.map && this.mapDiv) {
      this.geolocationService.getCurrentLocation().then(position => {
        const platform = new H.service.Platform({
          apikey: 'eyJhbGciOiJSUzUxMiIsImN0eSI6IkpXVCIsImlzcyI6IkhFUkUiLCJhaWQiOiJBZFphMVlUY2xWMWdaaUdmNFYxMSIsImlhdCI6MTcwMjAyMjU3MiwiZXhwIjoxNzAyMTA4OTcyLCJraWQiOiJqMSJ9.ZXlKaGJHY2lPaUprYVhJaUxDSmxibU1pT2lKQk1qVTJRMEpETFVoVE5URXlJbjAuLkRlOUkzcDdtMmJoWVF2dUJjVUt3NkEudHRDZFR4WUE0VTNNbXgyU1pkNTZKblozYklEeE5rVE84U3dTUDJSUmRUWF91VEZsYVkzNXZhVk9IUWQ3MnUtVmg4Ti12Z3Q3QS1wVXAxcHZVVGllLTZ6dWlROVowdVBYUFB3aG03VFhsUVBVQVVwNEloZ1hWeTdYMGJ1OUkzcWlGRnAzRTl2elJBRkZVNHBTTTBkSFFDVmRsaUpSQlZNWUtXNUx1MVZrNnFNLlp0ZVdPRlVJSk1YQlJxM0RmeDdsenUwZ0JRdUtWeHV3ZFdjenllRXlvNVk.c8hJ8O69IfhwASWDisCE4YZLtRlFVIVhb6FYAGqNoCBHvVppJi-tf5IJamxN07Gh7MFSB8Hi-UT6-6kPxiqwI0W7EVAYGPbk8WU62QimN3o6Hooj7__jllP4kCAC9ncAKHu_H267RGHeSNVcI2vRMfrJRWnIPAV9dUx1Ts6UA0-pMvGXqHKLqzG9eUXEzXenCU1U7mWklAFj7fQKzfky_ICnblC4SpEbmX_cLL5INdoUJGpNK_IQXkZmBbWH0B9PMmdy7-b_SK-hMeXSuxavM8tvuuaWHCcLDZZVp8dHBk5rif8yvSVbpmkMil9XFlxFKA7YND_59D2eud7ttbgAYQ'
        });
        const layers = platform.createDefaultLayers();
        const userLocation = {lat: position.coords.latitude, lng: position.coords.longitude};
        const map = new H.Map(
          this.mapDiv?.nativeElement,
          (layers as any).vector.normal.map,
          {
            pixelRatio: window.devicePixelRatio,
            center: userLocation,
            zoom: 10,
          },
        );
        if (this.mapDiv) {
          onResize(this.mapDiv.nativeElement, () => {
            map.getViewPort().resize();
          });
        }
        // Add event listener for mapviewchange
        map.addEventListener('mapviewchange', (ev: H.map.ChangeEvent) => {
          this.notify.emit(ev);
        });
        // Enable the interactive behaviour
        new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

        this.map = map;

      });
    }
  }

  private timeoutHandle: any;
  @Output() notify = new EventEmitter();

  ngOnChanges(changes: SimpleChanges) {
    clearTimeout(this.timeoutHandle);
    this.timeoutHandle = setTimeout(() => {
      if (this.map) {
        if (changes['zoom'] !== undefined) {
          this.map.setZoom(changes['zoom'].currentValue);
        }
        if (changes['lat'] !== undefined) {
          this.map.setCenter({lat: changes['lat'].currentValue, lng: this.lng});
        }
        if (changes['lng'] !== undefined) {
          this.map.setCenter({lat: this.lat, lng: changes['lng'].currentValue});
        }
      }
    }, 100);
  }

}
