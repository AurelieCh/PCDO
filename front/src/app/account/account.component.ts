import {Component, OnInit} from '@angular/core';

import { AuthService } from '@auth0/auth0-angular';
import { CommandeService } from '../service/commande.service';
import {ComposantsService} from "../service/composants.service";
import { CompteService } from '../service/compte.service';
import { FacturationService } from '../service/facturation.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit{
  profileJson: any;
constructor(public auth: AuthService, public compte: CompteService, public commande: CommandeService, public facture: FacturationService) {
}

  ngOnInit(): void {
    this.auth.user$.subscribe((profile) => {this.profileJson= profile; console.log(this.profileJson)}
  );
    //this.compte.getCompte()
}

  call(){

  }


}
