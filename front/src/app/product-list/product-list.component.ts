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



@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  ELEMENT_DATA: Compo[] | undefined;
  types= ListType;
  type: TypeComposant | undefined;

  dataSource : any;
  constructor(
              private route: ActivatedRoute, private router: Router, public auth: AuthService, public composants: ComposantsService, public compte: CompteService) {
    this.route.params.subscribe( params =>{
      if(this.types.find(element => element.url === params['type'])){
        this.type = this.types.find(element => element.url === params['type'])
      }else{
        this.router.navigateByUrl('/404');
      }
      }
    );
  }

  ngOnInit(): void {
    this.composants.getAllComposants().subscribe((result)=> {
        this.dataSource = result;
        console.log(result);
        // @ts-ignore
        if (this.type.nom === "ALL") {
          // @ts-ignore
          this.dataSource = this.dataSource.composants;
        } else {
          // @ts-ignore
          this.dataSource = this.dataSource.composants.filter((item) => item.categorie === this.type.nom);
        }
      }
    );
    }

  displayedColumns: string[] = ['nom', 'marque','description', 'prix', 'idComposant'];

  title: string ="";
}
