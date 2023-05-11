import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  constructor(private http: HttpClient) {

  }

  CreateCommande(composants: any[], email: string, adresse: string){
    const params = {
      composants,
      email,
      adresse
    };
    return this.http.post('http://localhost:8080/commandes/createCommande', params);
  }

  getCommande(id:number){
    return this.http.get('http://localhost:8080/commandes/getCommande?id=' +
      id);
  }
}
