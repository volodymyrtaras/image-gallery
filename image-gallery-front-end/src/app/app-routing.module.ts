import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {AuthguardService} from './services/auth/authguard.service';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthguardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
