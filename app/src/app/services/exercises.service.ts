import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exercise } from '../models/exercise';
import { TokenService } from './auth/token/token.service';
// import axios from 'axios'
@Injectable({
  providedIn: 'root'
})
export class ExercisesService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };
  exerciseURL = "http://localhost:8080/ejercicio";
  imagenURL = "http://localhost:8080/cloudinary";

  constructor(private httpClient: HttpClient,private tokenService: TokenService) {
  }
  async getExercises(){
    const resp = await fetch(`${this.exerciseURL}/lista`);
    const data = await resp.json();
    return data;
  }
  public detail(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.exerciseURL}/${id}`);
  }
  public save(exercise: any): Observable<any> {
    this.setHttpOptions()
    return this.httpClient.post<any>(`${this.exerciseURL}/create`, exercise, this.httpOptions);
  }
  public update(id: number, exercise: any): Observable<any> {
    return this.httpClient.put<any>(`${this.exerciseURL}/update/${id}`, exercise);
  }
  public delete(id: number): Observable<any> {
    this.setHttpOptions()
    return this.httpClient.delete<any>(`${this.exerciseURL}/delete/${id}`,this.httpOptions);
  }
  public uploadImage(imagen: File): Observable<any> {
    // this.setHttpOptions()
    console.log("Subiendo", imagen);

    const formData = new FormData();
    formData.append('multipartFile', imagen);

    return this.httpClient.post<any>(`${this.imagenURL}/upload`, formData);
  }
  private setHttpOptions(){
    this.httpOptions.headers =
    this.httpOptions.headers.set('Authorization', "Bearer "+this.tokenService.getToken() );
  }
}
