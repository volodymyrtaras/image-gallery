import {Injectable} from '@angular/core';
import {User} from '../../../model/user/user';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {paths} from '../../../resources/resources';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public login(user: User): Observable<any> {
    return this.http.post<any>(paths.api + '/' + paths.login, user);
  }

  public register(user: User): Observable<any> {
    return this.http.post<any>(paths.api + '/' + paths.register, user);
  }

  public logout(): Observable<any> {
    return this.http.post<any>(paths.api + '/' + paths.logout, new User());
  }

  public getCurrentUser(): Observable<User> {
    return this.http.get<User>(paths.api + '/' + paths.user_path);
  }
}
