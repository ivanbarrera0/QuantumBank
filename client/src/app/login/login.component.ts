import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Auth, RemoteService } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  remote:RemoteService;
  username:string;
  password:string;

  constructor(remote:RemoteService) {
    this.remote = remote;
    this.username = "";
    this.password = "";
  }

  loginUser() {

    if(this.username === "" || this.username.length > 255) {
      alert("Enter a valid username");
    } else if(this.password === "" || this.password.length > 255) {
      alert("Enter a valid password");
    } else {
      let auth:Auth = {
        username: this.username,
        password: this.password
      }
  
      this.remote.loginUser(auth)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.remote.redirect('dashboard');
        },
        error: (error: HttpErrorResponse) => {
          alert("Access Denied!");
          console.log(error);
        }
      })
    } 
  }
}
