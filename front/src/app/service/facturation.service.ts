import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FacturationService {

  constructor(private http: HttpClient) {

  }
/*
  CreateCompte(){
    return this.http.post('http://localhost:8084/facturations')
  }


    getSearchedComposants(filter: string ){
  return this.http.get('http://localhost:8084/facturations/getFacture?id='+ filter)
}*/
}
