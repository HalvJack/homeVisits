import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private webSocket: WebSocket;
  private receivedMessages: Subject<any> = new Subject<any>();

  constructor() {
    // Assuming you have a way to provide the URL here, for example, a constant or from environment variables
    const url: string = "https://demo4.traccar.org/";
    this.webSocket = new WebSocket(url);

    this.webSocket.onmessage = (event) => {
      this.receivedMessages.next(event.data);
    };


    this.webSocket.onclose = (event) => {
      console.log('WebSocket connection closed:', event);
    };

    this.webSocket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };
  }

  public sendMessage(message: string): void {
    this.webSocket.send(message);
  }

  public getMessages(): Observable<any> {
    return this.receivedMessages.asObservable();
  }

}
