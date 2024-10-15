import { Component } from '@angular/core';
import { RemoteService } from '../remote.service';
import { CurrentuserService } from '../currentuser.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  remote:RemoteService;
  currentUser:CurrentuserService;

  constructor(remote:RemoteService, currentUser:CurrentuserService) {
    this.remote = remote;
    this.currentUser = currentUser;
  }

  logout() {
    this.currentUser.clearUser();
    this.remote.redirect('/login');
  }
}
