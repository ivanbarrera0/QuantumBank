import { Injectable } from '@angular/core';
import { Account, User } from './remote.service';

@Injectable({
  providedIn: 'root'
})
export class CurrentuserService {

  username:string;
  email:string;
  phoneNumber:string;
  password:string;
  userType:string;
  accounts:Account[];

  constructor() {
    this.username = "";
    this.email = "";
    this.phoneNumber = "";
    this.password = "";
    this.userType = "";
    this.accounts = [];
  }

  getUsername():string {
    return this.username;
  }

  getEmail():string {
    return this.email;
  }

  getPhoneNumber():string {
    return this.phoneNumber;
  }

  getUserType():string {
    return this.userType;
  }

  getAccounts():Account[] {
    return this.accounts;
  }

  setCurrentUser(user:User) {
    this.username = user.username;
    this.email = user.email;
    this.phoneNumber = user.phoneNumber;
    this.password = user.password;
    this.userType = user.userType;
    this.accounts = user.accounts;
  }

  getCurrentUser():User {
    let user:User = {
      username: this.username,
      email: this.email,
      phoneNumber: this.phoneNumber,
      password: this.password,
      userType: this.userType,
      accounts: this.accounts
    }

    return user;
  }

  clearUser() {
    this.username = "";
    this.email = "";
    this.phoneNumber = "";
    this.password = "";
    this.userType = "";
    this.accounts = [];
  }
}
