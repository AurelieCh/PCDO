import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ComposantsService {

  constructor(private http: HttpClient) {

  }


  getAllComposants(){
    return this.http.get('http://localhost:8080/composants')
  }
}
