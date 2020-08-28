import {Component, OnInit} from '@angular/core';
import {paths} from '../../resources/resources';
import {UserService} from '../../services/rest/user/user.service';
import {User} from '../../model/user/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new User();
  message = '';

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  register(): void {
    this.userService.register(this.user).subscribe(
      data => {
        this.router.navigate([paths.login]);
        console.log(data);
      },
      error => {
        this.message = 'Bad credentials, please enter valid email and password';
        console.log(error);
      });
  }

  jumpToLogin(): void {
    this.router.navigate([paths.login]);
  }
}
