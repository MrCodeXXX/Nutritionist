import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule,MatIcon} from '@angular/material';
import {MatInputModule} from '@angular/material';
import { AuthenticationService } from './authentication.service';
import {MatCardModule} from '@angular/material/card';
import { AuthenticationRouterModule } from './authentication-router-module';



@NgModule({
  declarations: [LoginComponent, RegistrationComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
     HttpClientModule,
     AuthenticationRouterModule
 
  ],
  providers: [AuthenticationService],
  exports: [
    AuthenticationRouterModule
  ]

})
export class AuthenticationModule { }