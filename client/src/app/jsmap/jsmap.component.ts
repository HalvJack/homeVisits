import { Component, ViewChild, ElementRef, Input, SimpleChanges } from '@angular/core';
import H from '@here/maps-api-for-javascript';
import onResize from 'simple-element-resize-detector';
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
        apikey: 'eyJhbGciOiJSUzUxMiIsImN0eSI6IkpXVCIsImlzcyI6IkhFUkUiLCJhaWQiOiJBZFphMVlUY2xWMWdaaUdmNFYxMSIsImlhdCI6MTcwMTIwMDE1MywiZXhwIjoxNzAxMjg2NTUzLCJraWQiOiJqMSJ9'
      });
      const layers = platform.createDefaultLayers();
      const map = new H.Map(
        this.mapDiv?.nativeElement,
        (layers as any).vector.normal.map,
        {
          pixelRatio: window.devicePixelRatio,
          center: {lat: 0, lng: 0},
          zoom: 2,
        },
      );
      onResize(this.mapDiv.nativeElement, () =>{
        map.getViewPort().resize();
      })
      this.map = map;
    }
  }
  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;

  ngOnChanges(changes: SimpleChanges) {
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
  }

}
