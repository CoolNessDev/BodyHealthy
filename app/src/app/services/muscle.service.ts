import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Muscle } from '../models/muscle';

@Injectable({
  providedIn: 'root'
})
export class MuscleService {
  // muscleURL = "http://localhost:8080/musculo";
  muscleURL = "https://bodyhealthy.herokuapp.com/musculo";
  constructor(private httpClient: HttpClient) { }



  public getAllMuscles():Observable<Muscle>{
    return this.httpClient.get<Muscle>(`${this.muscleURL}/list`);
  }
}

