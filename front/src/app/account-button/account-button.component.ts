import { DOCUMENT } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-account-button',
  templateUrl: './account-button.component.html',
  styleUrls: ['./account-button.component.scss']
})
export class AccountButtonComponent {

  constructor(@Inject(DOCUMENT) public document: Document, public auth: AuthService) {}


  login() {
    this.auth.loginWithRedirect();
  }
}
