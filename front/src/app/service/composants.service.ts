import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ComposantsService {

  constructor(private http: HttpClient) {

  }

  getSearchedComposants(filter: string ){
    return this.http.get('http://localhost:8082/'+ filter)
  }

  getAllComposants(){
    return this.http.get('http://localhost:8082/composants')
  }
}






