import { Component } from '@angular/core';
import {ComposantsService} from "../service/composants.service";
import {delay} from "rxjs";
import {AuthService} from "@auth0/auth0-angular";
import {CompteService} from "../service/compte.service";

@Component({
  selector: 'app-build',
  templateUrl: './build.component.html',
  styleUrls: ['./build.component.scss']
})
export class BuildComponent {
  buildList: any;
  compteIds : any;
  cart: any;

  constructor(public auth: AuthService, public compte: CompteService,public compo: ComposantsService) {
    this.buildList = []


      this.compo.getBuild().subscribe( (result: any) => {

        this.buildList.push(result)

        this.compo.getBuild().subscribe( (result: any) => {
          this.buildList.push(result)
          this.compo.getBuild().subscribe( (result: any) => {

            this.buildList.push(result)

            this.compo.getBuild().subscribe( (result: any) => {
              this.buildList.push(result)
              this.compo.getBuild().subscribe( (result: any) => {

                this.buildList.push(result)

                this.compo.getBuild().subscribe( (result: any) => {
                  this.buildList.push(result)
                  this.compo.getBuild().subscribe( (result: any) => {

                    this.buildList.push(result)

                    this.compo.getBuild().subscribe( (result: any) => {
                      this.buildList.push(result)
                      this.compo.getBuild().subscribe( (result: any) => {

                        this.buildList.push(result)

                        this.compo.getBuild().subscribe( (result: any) => {
                          this.buildList.push(result)
                          this.compo.getBuild().subscribe( (result: any) => {

                            this.buildList.push(result)

                            this.compo.getBuild().subscribe( (result: any) => {
                              this.buildList.push(result)
                              this.compo.getBuild().subscribe( (result: any) => {

                                this.buildList.push(result)

                                this.compo.getBuild().subscribe( (result: any) => {
                                  this.buildList.push(result)
                                  this.compo.getBuild().subscribe( (result: any) => {

                                    this.buildList.push(result)

                                    this.compo.getBuild().subscribe( (result: any) => {
                                      this.buildList.push(result)

                                    })

                                  })
                                })

                              })
                            })

                          })
                        })

                      })
                    })

                  })
                })

              })
            })

          })
        })

      })




  }

  addToCart(build: any): void{
    this.auth.user$.subscribe((profile) => {
      this.compteIds=profile;
      // @ts-ignore
      this.compte.GetCompte(this.compteIds.email, this.compteIds.sub).subscribe(
        (result: any) => {
          this.cart = result.panier

          // @ts-ignore
          this.cart?.push(build.cpu.idComposant);
          this.cart?.push(build.gpu.idComposant);
          this.cart?.push(build.ram.idComposant);
          this.cart?.push(build.alim.idComposant);
          this.cart?.push(build.ssd.idComposant);
          this.cart?.push(build.hdd.idComposant);
          this.cart?.push(build.boitier.idComposant);
          this.cart?.push(build.carteMere.idComposant);
          if(this.cart !== undefined){
            let data =this.cartRequest(this.cart,this.compteIds.email)
            this.compte.addToCart(data).subscribe((result: any) => {
              // @ts-ignore
              this.compte.nbCart = this.cart.length
              console.log(result.status);
            })}
        })
    })
  }


  cartRequest(ids:[],email:String):String{
    let first=true;
    let result =""
    ids.forEach(id=>{
      if(first===true){
        result += "?compo="+id
        first = false
      }else{
        result += "&compo="+id
      }
    })
    result += "&email="+email
    return result
  }


}
