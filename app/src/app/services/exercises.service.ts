import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exercise } from '../models/exercise';
// import axios from 'axios'
@Injectable({
  providedIn: 'root'
})
export class ExercisesService {

  constructor(private httpClient: HttpClient) { }
  async getExercises(){
    const resp = await fetch('http://localhost:8080/ejercicio/lista');
    const data = await resp.json();
    // const { results: [user] } = data;
    console.log(data);
    // console.log('Ejercicio: ',data[0].nombre);
    return data;
  }
  // public getExercises(): Observable<any[]> {
  //   console.log("GAAAAAAAAAAAAAAAAAAAAAAAAAA");
  //   console.log(this.httpClient.get<any[]>('http://localhost:8080/ejercicio/lista'));
    
  //   return this.httpClient.get<any[]>('http://localhost:8080/ejercicio/lista');
  // }
  public save(exercise: any): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/ejercicio/create', exercise);
  }
  public update(id: number, exercise: any): Observable<any> {
    return this.httpClient.put<any>(`http://localhost:8080/ejercicio/${id}`, exercise);
  }
  public delete(id: number): Observable<any> {
    console.log("e2");
    return this.httpClient.delete<any>(`http://localhost:8080/ejercicio/delete/${id}`);
  }
}
