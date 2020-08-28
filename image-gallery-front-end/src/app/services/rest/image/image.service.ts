import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {paths} from '../../../resources/resources';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) {
  }

  public getAllImages(): Observable<any> {
    return this.http.get(paths.api + '/' + paths.image);
  }
}
