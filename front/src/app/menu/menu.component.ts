import { Component } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { ListType } from '../object/ListType';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
constructor(public auth: AuthService) {
}


}
