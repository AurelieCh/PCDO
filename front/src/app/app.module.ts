import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButton, MatButtonModule, MatIconButton} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import { MenuComponent } from './menu/menu.component';
import {MatCardModule} from "@angular/material/card";
import { ConfiPageComponent } from './confi-page/confi-page.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import { ProductListComponent } from './product-list/product-list.component';
import { MatTableModule } from '@angular/material/table'
import {MatTabsModule} from '@angular/material/tabs';
import { AuthModule } from '@auth0/auth0-angular';
import { AccountButtonComponent } from './account-button/account-button.component';
import { CartComponent } from './cart/cart.component';
import { AccountComponent } from './account/account.component';
import {MatBadgeModule} from '@angular/material/badge';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';




@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ConfiPageComponent,
    ProductListComponent,
    AccountButtonComponent,
    CartComponent,
    AccountComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatCardModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatGridListModule,
    MatTableModule,
    MatTabsModule,
    AuthModule.forRoot({
      domain: 'dev-vq2nkixrb8245ghu.us.auth0.com',
      clientId: 'aqwYHEe9p9oDbDbBSER4w1AbdGRm60Q4',
      authorizationParams: {
        redirect_uri: window.location.origin,
        audience:"http://localhost:8080"
      },
      httpInterceptor:{
        allowedList: ['http://localhost:8080/composants'],

      }

    }),
    MatBadgeModule,
    HttpClientModule

  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }
