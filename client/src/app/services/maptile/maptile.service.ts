import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MaptileService {

  private mapTileUrl = 'http://localhost:8080/maptiles';

  constructor(private http: HttpClient) {
  }

  getMapTile(): Observable<Blob> {
    return this.http.get(this.mapTileUrl, {responseType: 'blob'});
  }
}
