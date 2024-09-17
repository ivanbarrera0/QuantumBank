import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RemoteService {

  httpClient:HttpClient;
  baseURL:string;
  router:Router;

  constructor(httpClient:HttpClient, router:Router) { 
    this.httpClient = httpClient;
    this.router = router;
    this.baseURL = "http://localhost:8080";
  }

  redirect(url:string) {
    this.router.navigate([url]);
  }

  registerUser(user:User): Observable<HttpResponse<Object>> {

    return this.httpClient.post(this.baseURL + "/register/auth", user, {
      observe:'response',
      withCredentials: true,
      headers: new HttpHeaders ({
        'Content-Type': 'application/json'
      })
    })
  }

  loginUser(auth:Auth): Observable<HttpResponse<Object>> {

    return this.httpClient.post(this.baseURL + "/login/auth", auth, {
      observe: 'response',
      withCredentials: true,
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    })
  }
}

export interface User {
  username:string;
  email:string;
  phoneNumber:string;
  password:string;
  userType:string;
}

export interface Auth {
  username:string;
  password:string;
}
