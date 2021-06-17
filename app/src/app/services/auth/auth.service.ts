import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDTO } from 'src/app/models/jwtDto';
import { LoginUserDto } from 'src/app/models/loginUserDto';
import { User } from 'src/app/models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  authURL = 'http://localhost:8080/auth/';
  constructor(private httpClient: HttpClient) {}
  public login(loginUsuario: LoginUserDto): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUsuario);
  }
  public createUser(user: User): Observable<any> {
    return this.httpClient.post<any>(`${this.authURL}create`, user);
  }
}
