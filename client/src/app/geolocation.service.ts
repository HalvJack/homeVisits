import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GeolocationService {

  constructor(private httpClient: HttpClient) { }

  getCurrentLocation(): Promise<GeolocationPosition> {
    return new Promise((resolve, reject) => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(resolve, reject);
      } else {
        reject(new Error("Geolocation is not supported by this browser."));
      }
    });
  }

  sendDataToBackend(latitude: number, longitude: number): Observable<any> {
    const url = 'http://localhost:8080/location'; // Your Spring Boot endpoint
    return this.httpClient.post(url, { latitude, longitude });
  }
}
