import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ComposantsService {

  constructor(private http: HttpClient) {

  }


  getSearchedComposants(filter: string ){
    return this.http.get('http://localhost:8080/composants/'+ filter)
  }

  getComposants(min: number, max: number, nom: string,marque: string,desc: string, categorie: string, list: any){
    return this.http.post('http://localhost:8080/composants/search', {
      prixMin:min,
      prixMax:max,
      nom: nom,
      marque: marque,
      description: desc,
      categorie: categorie,
      caracteristiqueList: list

    })
  }

  getBuild(){
    return this.http.get("http://localhost:8080/autobuild/random")
  }



}






