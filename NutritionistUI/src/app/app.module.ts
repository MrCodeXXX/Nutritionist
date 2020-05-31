import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatButton } from '@angular/material/button'
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { FoodModule } from './modules/food/food.module';
import { AuthGuardService } from './auth-gurd.service';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
const appRoutes:Routes = [

  
]



 @NgModule({
   declarations: [
     AppComponent,
     MainNavComponent
   ],
   imports :[
     AuthenticationModule,
     FoodModule,
  BrowserModule,
  BrowserAnimationsModule,
  MatToolbarModule,FormsModule,
  MatDialogModule,
  MatButtonModule,
 
RouterModule.forRoot(appRoutes),
 
LayoutModule,
 
MatSidenavModule,
 
MatIconModule,
 
MatListModule],
providers: [AuthGuardService],
   bootstrap: [AppComponent]
 })
export class AppModule { }

