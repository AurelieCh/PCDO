import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ComposantsService {

  constructor(private http: HttpClient) {

  }

  getSearchedComposants(filter: string ){
    return this.http.get('http://localhost:8080/'+ filter)
  }

  getAllComposants(){
    return this.http.get('http://localhost:8080/composants')
  }
}


this.http.get(http://localhost:8080/commandes/)
this.http.get(http://localhost:8080/facturations/)
this.http.get(http://localhost:8080/comptes/)



