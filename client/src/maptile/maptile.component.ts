import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {MaptileService} from "./maptile.service";

@Component({
  selector: 'app-maptile',
  templateUrl: './maptile.component.html',
  styleUrls: ['./maptile.component.sass']
})
export class MaptileComponent implements OnInit{
  mapTileUrl!: SafeUrl;

  constructor(
    private mapTileService: MaptileService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
    this.mapTileService.getMapTile().subscribe(blob => {
      const objectURL = URL.createObjectURL(blob);
      this.mapTileUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    });
  }
}
