import { Component, Input, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Router } from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';
import { ListType } from '../object/ListType';
import { TypeComposant } from '../object/TypeComposant';
import { ComposantsService } from '../service/composants.service';
import { CompteService } from '../service/compte.service';

export interface Compo {
  idComposant: number,
  prix: number,
  nom: string,
  marque: string,
  description: string,
  url: string,
  categorie: string,
  caracteristiqueList: any[]
}

class Filter {
  prixMin=0
  prixMax=99999
  nom= ""
  marque= ""
  description= ""
  categorie= ""
  caracteristiqueList= []

}


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent {

  types= ListType;
  type: TypeComposant | undefined;

  dataSource : any;
  filtre : Filter | undefined;
  constructor(
              private route: ActivatedRoute, private router: Router, public auth: AuthService, public composants: ComposantsService, public Compte: CompteService) {
    this.route.params.subscribe( params =>{
      if(this.types.find(element => element.url === params['type'])){
        this.type = this.types.find(element => element.url === params['type'])
         // @ts-ignore
        console.log(this.type.nom)

        // @ts-ignore
        this.composants.getComposants(0,99999,"","","",this.type.nom,[]).subscribe((result)=> {
            this.dataSource = result;

            // @ts-ignore
            this.dataSource = this.dataSource.composants;

          }
        );
      }else{
        this.router.navigateByUrl('/404');
      }
      }
    );

  }



  displayedColumns: string[] = ['image', 'nom', 'marque','description', 'prix', 'idComposant'];

  title: string ="";
  compteIds:any;
  cart: [] | undefined;

  cartRequest(ids:[],email:String):String{
    let first=true;
    let result =""
    ids.forEach(id=>{
      if(first===true){
        result += "?compo="+id
        first = false
      }else{
        result += "&compo="+id
      }
    })
    result += "&email="+email
    return result
  }

  addToCart(id: number): void{
    this.auth.user$.subscribe((profile) => {
      this.compteIds=profile;
      // @ts-ignore
      this.Compte.GetCompte(this.compteIds.email, this.compteIds.sub).subscribe(
          (result: any) => {
          this.cart = result.panier
            console.log(this.cart)
            // @ts-ignore
            this.cart?.push(id);

          if(this.cart !== undefined){
            let data =this.cartRequest(this.cart,this.compteIds.email)
            this.Compte.addToCart(data).subscribe((result: any) => {
                // @ts-ignore
              this.Compte.nbCart = this.cart.length
                console.log(result.status);
              })}
          })
    })
        }

}
