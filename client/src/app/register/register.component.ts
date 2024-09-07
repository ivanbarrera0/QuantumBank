import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RemoteService, User } from '../remote.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  username:string;
  email:string;
  phoneNumber:string;
  password:string;
  userType:string;
  remote:RemoteService;

  constructor(remote:RemoteService) {
    this.remote = remote;
    this.username = "";
    this.email = "";
    this.phoneNumber = "";
    this.password = "";
    this.userType = "Personal";
  }

  registerUser() {

    let user: User = {
      username: this.username,
      email: this.email,
      phoneNumber: this.phoneNumber,
      password: this.password,
      userType: this.userType
    }

    this.remote.registerUser(user)
    .subscribe({
      next: (data) => {
        alert("User registered!");
        console.log(data);
      },
      error: (error: HttpErrorResponse) => {
        alert("Couldn't register...");
        console.log(error.error);
      }
    })
  }
}
