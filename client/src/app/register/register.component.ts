import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Account, RemoteService, User } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username:string;
  email:string;
  phoneNumber:string;
  password:string;
  confirmPassword:string;
  userType:string;
  accounts:Account[];
  remote:RemoteService;

  constructor(remote:RemoteService) {
    this.remote = remote;
    this.username = "";
    this.email = "";
    this.phoneNumber = "";
    this.password = "";
    this.confirmPassword = "";
    this.userType = "";
    this.accounts = [];
  }

  registerUser() {

    if(this.username === "" || this.username.length > 255) {
      alert("Enter a valid username!");
    } else if(this.email === "" || this.email.length > 255) {
      alert("Enter a valid email!");
    } else if(this.phoneNumber === "" || this.phoneNumber.length > 15) {
      alert("Enter a valid phone number!");
    } else if(this.password === "" || this.password.length > 255) {
      alert("Enter a valid password!");
    } else if(this.confirmPassword === "" || this.confirmPassword.length > 255) {
      alert("Enter a valid input");
    } else if(this.password !== this.confirmPassword) {
      alert("Confirm Pasword differs from Password!");
    } else {
      let user: User = {
        username: this.username,
        email: this.email,
        phoneNumber: this.phoneNumber,
        password: this.password,
        userType: this.userType,
        accounts: this.accounts
      }
  
      this.remote.registerUser(user)
      .subscribe({
        next: (data) => {
          alert("User registered!");
          console.log(data);
          this.remote.redirect('/login');
        },
        error: (error: HttpErrorResponse) => {
          alert("Couldn't register...");
          console.log(error.error);
        }
      })
    }
  }    
}
