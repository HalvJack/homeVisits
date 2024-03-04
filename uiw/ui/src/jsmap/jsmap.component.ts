import {Component, ViewChild, ElementRef, Input, SimpleChanges, Output, EventEmitter} from '@angular/core';
import {GeolocationService} from "../app/geolocation.service";
import H from '@here/maps-api-for-javascript';
import onResize from 'simple-element-resize-detector';
import {environment} from "../environment";
import {WebSocketService} from "../app/web-socket.service";

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
          apikey: environment.hereMapsApiKey
        });
        const layers = platform.createDefaultLayers();
        const userLocation = {lat: position.coords.latitude, lng: position.coords.longitude};
        const map = new H.Map(
          this.mapDiv?.nativeElement,
          (layers as any).vector.normal.map,
          {
            pixelRatio: window.devicePixelRatio,
            center: userLocation,
            zoom: 12,
          },
        );


        const userMarker = new H.map.Marker(userLocation);
        map.addObject(userMarker);


        // Add doctor location marker
        const doctorLocation = {lat: (position.coords.latitude - 0.005), lng: position.coords.longitude + 0.005};
        const doctorMarker = new H.map.Marker(doctorLocation);
        map.addObject(doctorMarker);

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
