import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';

interface Compte{
  email:string,
  password:string
}
@Injectable({
  providedIn: 'root'
})
export class CompteService {
  constructor(private http: HttpClient) {

  }


  checkCompte(email:string){
    // @ts-ignore
    return this.http.get('http://localhost:8080/comptes/checkMail?email='+email,
      {observe: 'response'});
  }

  CreateCompte(nom: string,
      prenom: string,
      email: string,
      password: string,
      password2: string,
      adresse: string){
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    const params = new HttpParams().set('nom', nom).set('prenom', prenom)
      .set('email', email).set('password', password).set('password2', password2)
      .set("adresse", adresse);
    return this.http.post('http://localhost:8080/comptes', params, {headers: headers});
    }

    GetCompte(email: string, password: string){

      return this.http.get("http://localhost:8080/comptes/getCompte", {email: email, });
    }

/*
  ModifyCompte(){
    return this.http.put('http://localhost:8083/comptes/getCompte')
  }*/



}
