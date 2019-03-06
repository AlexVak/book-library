import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  sendCredential(username: string, password: string) {
    const url = environment.serverUrl + '/token';
    const encodedCredentials = btoa(username + ':' + password);
    const basicHeader = 'Basic ' + encodedCredentials;
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: basicHeader
    });
    return this.http.get(url, {headers});
  }

  checkSession() {
    const url = environment.serverUrl + '/checkSession';
    const headers = new HttpHeaders({
        'x-auth-token': localStorage.getItem('x-AuthToken')
      })
    ;
    return this.http.get(url, {headers});
  }

  logout() {
    const url = environment.serverUrl + '/user/logout';
    const headers = new HttpHeaders({
        'x-auth-token': localStorage.getItem('x-AuthToken')
      })
    ;
    return this.http.post(url, '', {headers});
  }

}
