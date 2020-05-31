import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const authRouter : Routes = [
    {
      path:'',
      children: [
        {
          path: '',
          redirectTo: 'login',
          pathMatch: 'full'
        },
        {
          path: 'register',
          component: RegistrationComponent,
        },
        {
          path: 'login',
          component: LoginComponent,
        }
      ]
    }
  ];
  
  @NgModule({
    imports: [
      RouterModule.forChild(authRouter)
    ],
    exports: [
      RouterModule
    ]
  })
  export class AuthenticationRouterModule { }
  