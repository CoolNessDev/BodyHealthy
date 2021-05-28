import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Routine } from '../models/routine';
import { TokenService } from './auth/token/token.service';

@Injectable({
  providedIn: 'root',
})
export class RoutineService {
  httpOptions = {
    headers: new HttpHeaders({
      Authorization: 'my-auth-token',
    }),
  };
  routineURL = 'http://localhost:8080/rutina';
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}
  public getDefaultRoutinesByLevel(level: string): Observable<Routine[]> {
    return this.httpClient.get<Routine[]>(`${this.routineURL}/listdefault/${level}`);
  }
  public getRoutine(id: number): Observable<Routine> {
    return this.httpClient.get<Routine>(`${this.routineURL}/${id}`);
  }
  public getRoutinesByUser(id: Number): Observable<any[]> {
    this.setHttpOptions();
    return this.httpClient.get<any[]>(
      `${this.routineURL}/user/${id}`,
      this.httpOptions
    );
  }
  public postRoutine(routine: Routine): Observable<Routine> {
    this.setHttpOptions();
    return this.httpClient.post<Routine>(
      `${this.routineURL}/create`,
      routine,
      this.httpOptions
    );
  }
  public postRoutineDefault(routine: Routine): Observable<Routine> {
    this.setHttpOptions();
    return this.httpClient.post<Routine>(
      `${this.routineURL}/default`,
      routine,
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
