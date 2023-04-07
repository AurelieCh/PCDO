import { HttpClient, HttpParams } from '@angular/common/http';
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
  getCompte(email:string,password:string){
    const params = new HttpParams({ fromObject: {
        email: email,
        password: password
      } });
    // @ts-ignore
    return this.http.get('http://localhost:8083/comptes/getCompte', params);
  }
/*
  CreateCompte(){
    return this.http.post('http://localhost:8083/comptes/getCompte')
  }

  ModifyCompte(){
    return this.http.put('http://localhost:8083/comptes/getCompte')
  }

*/

}
