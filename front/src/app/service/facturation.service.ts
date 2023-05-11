import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {AbstractControl, ValidationErrors, ɵElement, ɵValue} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class FacturationService {

  constructor(private http: HttpClient) {

  }

  CreateFacturation(commande: number, email: string, adresse: string, typePaiement: ɵValue<ɵElement<(string | ((control: AbstractControl) => (ValidationErrors | null)))[], null>> | undefined, prix: number){
    const params = {
      email,
      commande,
      adresse,
      typePaiement,
      prix
    };
    return this.http.post('http://localhost:8080/facturations', params);
  }



getFacture(id:number){
  return this.http.get('http://localhost:8080/facturations/getFacture?id='+id);
}


}
