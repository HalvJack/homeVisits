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
          apikey: '9PtJH_3GhoKA2I6PThd8kq0Qr-2IbWd9nw7NCnDG8bg'
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
