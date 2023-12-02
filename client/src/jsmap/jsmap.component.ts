import { Component, ViewChild, ElementRef, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';
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
      // Add event listener for mapviewchange
      map.addEventListener('mapviewchange', (ev: H.map.ChangeEvent) => {
        this.notify.emit(ev);
      });
      // Enable the interactive behaviour
      new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

      this.map = map;

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
