import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/rest/user/user.service';
import {User} from '../../model/user/user';
import {Router} from '@angular/router';
import {paths} from '../../resources/resources';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  message = '';

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  login(): void {
    this.userService.login(this.user).subscribe(
      data => {
        this.router.navigate([paths.home]);
        window.sessionStorage.setItem('loggedIn', 'true');
        console.log(data);
      },
      error => {
        this.message = 'Bad credentials, please enter valid email and password';
        console.log(error);
      });
  }

  jumpToRegistration(): void {
    this.router.navigate([paths.register]);
  }
}
