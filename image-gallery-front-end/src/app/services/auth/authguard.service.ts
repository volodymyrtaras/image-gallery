import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {paths} from '../../resources/resources';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const canGo = this.canGoToRoute(route.toString());
    if (canGo === false) {
      this.jumpToLogin();
    }

    return canGo;
  }

  canGoToRoute(route: string): boolean {
    return window.sessionStorage.getItem('loggedIn') === 'true';
  }

  jumpToLogin(): void {
    this.router.navigate([paths.login]);
  }
}
