import { Component } from '@angular/core';
import { CurrentuserService } from '../currentuser.service';
import { RemoteService } from '../remote.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './deposit.component.html',
  styleUrl: './deposit.component.css'
})
export class DepositComponent {

  currentUser:CurrentuserService;
  remote:RemoteService;

  constructor(currentUser:CurrentuserService, remote:RemoteService) {
    this.currentUser = currentUser;
    this.remote = remote;
  }
}
