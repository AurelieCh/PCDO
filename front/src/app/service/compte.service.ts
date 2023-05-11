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

  public nbCart = 0;
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
    const params = {
      nom,
      prenom,
      email,
      password,
      password2,
      adresse
    };
    return this.http.post('http://localhost:8080/comptes', params);
    }

    GetCompte(email: string, password: string){
      const params = {
        email,
        password
      }



      // @ts-ignore
      return this.http.put("http://localhost:8080/comptes/getCompte", params);
    }


  addToCart(data: String){

    // @ts-ignore
    return this.http.put("http://localhost:8080/comptes/updatePanier"+data);
  }
  emptyCart(email: String){

    // @ts-ignore
    return this.http.put("http://localhost:8080/comptes/emptyPanier?email="+email);
  }
/*
  ModifyCompte(){
    return this.http.put('http://localhost:8083/comptes/getCompte')
  }*/

  addCommande(email :string,id:number){
    return this.http.put("http://localhost:8080/comptes/addCommande?id="+id+"&email="+email, {});
  }

  addFacture(email :string,id:number){
  return this.http.put("http://localhost:8080/comptes/addFacture?id="+id+"&email="+email, {});
  }
}
