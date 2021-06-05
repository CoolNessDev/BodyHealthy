import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Publication } from '../models/publication';
import { TokenService } from './auth/token/token.service';

@Injectable({
  providedIn: 'root',
})
export class PublicationService {
  // httpOptions = {
  //   headers: new HttpHeaders({
  //     Authorization: 'my-auth-token',
  //   }),
  // };
  publicationURL = 'http://localhost:8080/publicacion';
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}
  public getPublicationsByPages(page: number, size: number, order: string, asc: boolean): Observable<any>{
    return this.httpClient.get<any>(`${this.publicationURL}/pageable?` + `page=${page}&size=${size}&order=${order}&asc=${asc}`);
  }
  public getPublication(id: number): Observable<Publication>{
    return this.httpClient.get<Publication>(`${this.publicationURL}/${id}`);
  }
  public getPublicationsByUser(id: number): Observable<Publication[]>{
    return this.httpClient.get<Publication[]>(`${this.publicationURL}/usuario/${id}`);
  }
  // private setHttpOptions() {
  //   this.httpOptions.headers = this.httpOptions.headers.set(
  //     'Authorization',
  //     'Bearer ' + this.tokenService.getToken()
  //   );
  // }
}
