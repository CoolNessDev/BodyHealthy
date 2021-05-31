import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Commentary } from '../models/commentary';

@Injectable({
  providedIn: 'root',
})
export class CommmentaryService {
  comentaryURL = 'http://localhost:8080/comentario';
  constructor(private httpClient: HttpClient) {}
  public getComentariesByPublication(id: number): Observable<Commentary[]> {
    return this.httpClient.get<Commentary[]>(
      `${this.comentaryURL}/publicacion/${id}`
    );
  }
}
