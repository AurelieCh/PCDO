import {Component, OnInit} from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import {CompteService} from "../service/compte.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit{
  constructor(public auth: AuthService, public Compte: CompteService) {
  }

  cart: any;
  compteIds:any
  ngOnInit(): void {
      this.auth.user$.subscribe((profile) => {
        this.compteIds=profile;
        this.Compte.GetCompte(this.compteIds.email, this.compteIds.sub).subscribe(
          (result) => {
            console.log(result)
            this.cart=result
          }
        );
      });

  }


}
