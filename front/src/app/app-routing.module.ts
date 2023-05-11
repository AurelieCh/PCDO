import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { CartComponent } from './cart/cart.component';
import { ConfiPageComponent } from './confi-page/confi-page.component';
import {MenuComponent} from "./menu/menu.component";
import { ProductListComponent } from './product-list/product-list.component';
import {BuildComponent} from "./build/build.component";


const routes: Routes = [{ path: '', component: MenuComponent },
  { path: 'config', component: ConfiPageComponent },
  { path: 'list', component: ProductListComponent },
  { path: 'list/:type', component: ProductListComponent },
  { path: 'prebuild', component: BuildComponent },
  { path: 'cart', component: CartComponent },
  { path: 'account', component: AccountComponent },
  { path: '404', redirectTo: '' },





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
