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
      Authorization: 'my-auth-token'
    })
  };
  exerciseURL = "http://localhost:8080/ejercicio";
  imagenURL = "http://localhost:8080/cloudinary";

  constructor(private httpClient: HttpClient,private tokenService: TokenService) {
  }
  public getExercises():Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.exerciseURL}/lista`);
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
  private setHttpOptions(){
    this.httpOptions.headers =
    this.httpOptions.headers.set('Authorization', "Bearer "+this.tokenService.getToken() );
  }
  //Mover
  public uploadImage(imagen: File): Observable<any> {
    this.setHttpOptions()
    const formData = new FormData();
    formData.append('multipartFile', imagen);

    return this.httpClient.post<any>(`${this.imagenURL}/upload`, formData, this.httpOptions);
  }
  public deleteImage(id: String): Observable<any> {
    console.log("deleting: ",id);
    this.setHttpOptions()
    return this.httpClient.delete<any>(`${this.imagenURL}/delete/${id}`,this.httpOptions);
  }
}
