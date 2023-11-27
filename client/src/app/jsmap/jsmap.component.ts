import {Component, ElementRef, ViewChild} from '@angular/core';
import * as H from '@here/maps-api-for-javascript';
@Component({
  selector: 'app-jsmap',
  templateUrl: './jsmap.component.html',
  styleUrls: ['./jsmap.component.sass']
})
export class JsmapComponent {
  private map?: H.Map;

  @ViewChild('map') mapDiv?: ElementRef;

  ngAfterViewInit(): void{
    if(!this.map && this.mapDiv){
      const platform = new H.service.Platform({
        apikey: '{YOUR_API_KEY}'
      });
      const layers = platform.createDefaultLayers();
      const map = new H.map(
        this.mapDiv?.nativeElement,
        (layers as any).vector.normal.map,
        {
          pixelRatio: window.devicePixelRatio,
          center: {lat: 0, lng: 0},
          zoom: 2,
        },
      );
      this.map = map;
    }
  }
}
