import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Commentary } from '../models/commentary';
import { TokenService } from './auth/token/token.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class CommmentaryService {
  httpOptions = {
    headers: new HttpHeaders({
      Authorization: 'my-auth-token',
    }),
  };
  comentaryURL = 'http://localhost:8080/comentario';
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService,
    private userService: UserService
  ) {}
  public getComentariesByPublication(id: number): Observable<Commentary[]> {
    return this.httpClient.get<Commentary[]>(
      `${this.comentaryURL}/publicacion/${id}`
    );
  }
  public postCommentary(commentary: Commentary): Observable<any> {
    this.setHttpOptions();
    return this.httpClient.post<any>(
      `${this.comentaryURL}/create`,
      commentary,
      this.httpOptions
    );
  }
  public deleteCommentary(id: number): Observable<any> {
    this.setHttpOptions();
    return this.httpClient.delete<any>(
      `${
        this.comentaryURL
      }/delete?id=${id}&idUsuario=${this.userService.getUserId()}`,
      this.httpOptions
    );
  }
  private setHttpOptions() {
    this.httpOptions.headers = this.httpOptions.headers.set(
      'Authorization',
      'Bearer ' + this.tokenService.getToken()
    );
  }
}
