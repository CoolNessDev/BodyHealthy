import { Component, OnInit } from '@angular/core';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent implements OnInit {
  exercises!: any;
  constructor(private exercisesService: ExercisesService ) { 
    // this.exercises = exercisesService.getExercises();
  }

  ngOnInit(): void {
    this.fetchExercise();
  }
  private async fetchExercise() {
    this.exercises = await this.exercisesService.getExercises();
  }
}
