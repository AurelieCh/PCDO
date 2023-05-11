import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import {CompteService} from "../service/compte.service";

@Component({
  selector: 'app-account-button',
  templateUrl: './account-button.component.html',
  styleUrls: ['./account-button.component.scss']
})
export class AccountButtonComponent {
  private profileJson: any | undefined;
  public cartNumber: any;

  constructor(@Inject(DOCUMENT) public document: Document, public auth: AuthService,public compte: CompteService) {
    this.auth.user$.subscribe((profile) => {
      this.profileJson=profile;
      // @ts-ignore
      this.compte.GetCompte(this.profileJson.email, this.profileJson.sub).subscribe(
        (result: any) => {
          this.compte.nbCart = result.panier.length
          console.log(this.cartNumber)

        }
      );
    });
  }


  login() {
    this.auth.loginWithRedirect();
  }
}
