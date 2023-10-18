import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getData(id: number){
    return this.http.get(`${this.apiUrl}/doctor/1`);
  }
}
