import {AuthenticationService } from './../authentication/authentication.service';
import { Injectable } from '@angular/core';
import {HttpRequest,
   HttpHandler,
HttpEvent,
HttpInterceptor } from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable()
export class TokenInterceptor implements HttpInterceptor{
    constructor(private auth:AuthenticationService){

    }
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  console.log("In Intercept");
  if(request.url.startsWith("https://api.nal.usda.gov/ndb/search/?") || request.url.startsWith("https://api.nal.usda.gov/ndb/V2/reports?")){

  }else{
    request = request.clone({
        setHeaders: {
            Authorization: `Bearer ${this.auth.getToken()}`
        }
    });
  }
    

     console.log(` ${this.auth.getToken()}`)

     return next.handle(request);
    }
}