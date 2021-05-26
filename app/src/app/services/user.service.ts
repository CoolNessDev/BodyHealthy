import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  httpOptions = {
    headers: new HttpHeaders({
      Authorization: 'my-auth-token',
    }),
  };
  userURL = 'http://localhost:8080/usuario';
  constructor(private httpClient: HttpClient) {}

  public getUser(email: string): Observable<User> {
    return this.httpClient.get<User>(`${this.userURL}/${email}`);
  }
}
