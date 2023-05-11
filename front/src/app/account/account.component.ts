import {Component, Inject} from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { CommandeService } from '../service/commande.service';
import {ComposantsService} from "../service/composants.service";
import { CompteService } from '../service/compte.service';
import { FacturationService } from '../service/facturation.service';
import {Router} from "@angular/router";





@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {
  compteIds: any;
  info: any;

  couple: any;


  constructor( private router: Router ,public auth: AuthService, public compte: CompteService, public commande: CommandeService, public facture: FacturationService,public Composant: ComposantsService ) {
    this.couple = [];
    this.auth.user$.subscribe((profile) => {
        this.compteIds = profile;
        // @ts-ignore
        this.compte.GetCompte(this.compteIds.email, this.compteIds.sub).subscribe(
          (result: any) => {
            this.info = result
            this.info.facturations.forEach((id: number) => {
              this.facture.getFacture(id).subscribe((facture_: any) => {
                console.log(facture_)
                this.commande.getCommande(facture_.commande).subscribe((commande_: any) => {
                  let compoList: any[];
                  compoList=[]
                  let i = 0;
                  commande_.composants.forEach((compo: string) => {
                    this.Composant.getSearchedComposants(compo).subscribe(
                      (compo : any) => {
                        compo.prix = facture_.tousPrix[i]
                        compoList.push(compo)
                        i++
                      }

                    )

                  })
                  this.couple.push({facture_, commande_, compoList})


                })
                })
              })

            })


          })
      }


  openDialog(id:any): void {

    // @ts-ignore
    let printContents = document.getElementById(id).innerHTML;
    let originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
  }


}




@Pipe({ name: 'removeUnderscore' })
export class RemoveUnderscorePipe implements PipeTransform {
  transform(value: any, args?: any): any {
    return value.replace(/_/g, " ");
  }
}



