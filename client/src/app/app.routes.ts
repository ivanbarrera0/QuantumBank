import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NotfoundComponent } from './notfound/notfound.component';

export const routes: Routes = 
[{path: "", component: RegisterComponent},
{path: "register", component: RegisterComponent},
{path: "login", component: LoginComponent},
{path: "dashboard", component: DashboardComponent},
{path: "**", component: NotfoundComponent}
];
