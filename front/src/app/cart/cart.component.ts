import {Component, Inject, ViewChild} from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import {CompteService} from "../service/compte.service";
import {ComposantsService} from "../service/composants.service";
import {MatTable} from "@angular/material/table";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {FormBuilder, Validators} from "@angular/forms";
import {CommandeService} from "../service/commande.service";
import {FacturationService} from "../service/facturation.service";




export interface DialogData {
  Compte : any,
  prix : number
}
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent{

  @ViewChild(MatTable) table: MatTable<any> | undefined;

  cart: any | undefined;

  cartList: any;
  cartList2: any;
  compteIds:any;

  accountDiag: any;

  somme=0

  constructor(public auth: AuthService, public Compte: CompteService, public Composant: ComposantsService, public dialog: MatDialog) {
      this.auth.user$.subscribe((profile) => {
        this.compteIds=profile;
        // @ts-ignore
        this.cartList = []
        this.Compte.GetCompte(this.compteIds.email, this.compteIds.sub).subscribe(
          (result : any) => {
            this.cart = result.panier
            this.accountDiag = result
            this.cart.forEach((compo: string) => {
              this.Composant.getSearchedComposants(compo).subscribe(
                (result : any) => {
                  this.cartList.push(result)
                  this.somme+=result.prix
                  if(this.cartList.length===this.Compte.nbCart){
                    this.cartList2=this.cartList
                  }
                }

              )
            })

          }
        );
      });
  }




  displayedColumns: string[] = ['image', 'nom', 'marque','description', 'prix', 'idComposant'];

  cartRequest(ids:[],email:String):String{
    let first=true;
    let result =""
    ids.forEach(id=>{
      if(first){
        result += "?compo="+id
        first = false
      }else{
        result += "&compo="+id
      }
    })
    result += "&email="+email
    return result
  }

  deleteToCart(id: number): void{
    this.auth.user$.subscribe((profile) => {
      this.compteIds=profile;
      let index = this.cart.indexOf(id)
      if (index > -1) { // only splice array when item is found
        this.cart.splice(index, 1); // 2nd parameter means remove one item only
      }
            let data =this.cartRequest(this.cart,this.compteIds.email)
            this.Compte.addToCart(data).subscribe((result: any) => {
              // @ts-ignore
              this.Compte.nbCart = this.cart.length
              // @ts-ignore
              this.table.renderRows();
              console.log(result.status);
            })});
  }


  openDialog(): void {
    const dialogRef = this.dialog.open(DialogCommandeDialog, {
      data: {
        Compte : this.accountDiag,
        prix : this.somme
      },
    });

  }
}




@Component({
  selector: 'dialog-content',
  templateUrl: 'dialog-content.html',
})
export class DialogCommandeDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogCommandeDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public compte: CompteService, private _formBuilder: FormBuilder,
  public commande: CommandeService, public facture: FacturationService) {
    console.log(this.data.Compte)
    console.log(this.data.prix)
  }

  firstFormGroup = this._formBuilder.group({
    firstCtrl: [this.data.Compte.adresse, Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });


  pay: any[] = [
    {value: 0, viewValue: 'Carte'},
    {value: 1, viewValue: 'Paypal'},
    {value: 2, viewValue: 'Plusieurs Fois'},
    {value: 3, viewValue: 'Virement'},
  ];



  createCommande(){
    this.commande.CreateCommande(this.data.Compte.panier, this.data.Compte.email, this.firstFormGroup.value.firstCtrl).subscribe(
      (commande:any) => {

          this.compte.emptyCart(this.data.Compte.email).subscribe(
            (panier:any) => {});
        this.compte.nbCart = 0
          this.dialogRef.close();
       });

  }



}
