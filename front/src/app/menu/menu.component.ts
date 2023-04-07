import { Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { ListType } from '../object/ListType';
import { CompteService } from '../service/compte.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';


export interface DialogData {
  nom: string,
  prenom: string,
  email: string,
  password: string,
  password2: string,
  adresse: string,
}
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  constructor(public auth: AuthService, public compte: CompteService, public dialog: MatDialog) {
  }

  profile: any;

  ngOnInit(): void {
    this.auth.user$.subscribe((profile) => {
        this.profile = profile;
        console.log(this.profile)
      }
    );
    if (this.profile != null) {
      this.compte.getCompte(this.profile.email, this.profile.sub).subscribe(
        (profile) => {
          if (profile === null) {
            this.openDialog();
          }
        }
      );

    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogContentDialog, {
      data: {
        nom: this.profile.name,
        prenom: this.profile.name,
        email: this.profile.email,
        password: this.profile.sub,
        password2: this.profile.sub,
        adresse: "",
      },
    });

  }
}

@Component({
  selector: 'dialog-content',
  templateUrl: 'dialog-content.html',
})
export class DialogContentDialog {


  constructor(
    public dialogRef: MatDialogRef<DialogContentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {}


}
