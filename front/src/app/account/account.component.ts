import {Component, OnInit} from '@angular/core';

import { AuthService } from '@auth0/auth0-angular';
import {ComposantsService} from "../service/composants.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit{
  profileJson: string = '';
constructor(public auth: AuthService, public compo: ComposantsService) {
}

  ngOnInit(): void {
    this.auth.user$.subscribe((profile) => this.profileJson= JSON.stringify (profile, null, 2)
  );}

  call(){
  this.compo.getAllComposants().subscribe((result)=>console.log(result))
  }

}
