import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-exercise-muscle',
  templateUrl: './exercise-muscle.component.html',
  styleUrls: ['./exercise-muscle.component.css'],
})
export class ExerciseMuscleComponent implements OnInit {
  exercises!: any;
  loaded: boolean;
  error: boolean;
  constructor(
    private exercisesService: ExercisesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params.id;
    console.log("path: ",id);
    if(id!=undefined){
      this.onFetch(id);
    }
  }

  onFetch(muscleId:number) {
    this.router.navigate([`/ejercicios/musculos/${muscleId}`]);
    this.fetchExercise();

  }
  private fetchExercise() {
    const id = this.activatedRoute.snapshot.params.id;
    console.log(id);
    this.loaded = false;
    this.error = false;
    this.exercisesService.getExercisesByMuscle(id).subscribe(
      (data) => {
        this.loaded = true;
        this.exercises = data;
        window.location.hash = '#grid';
      },
      (err) => {
        this.loaded = true;
        this.error = true;
        console.log('Error: ', err.message);
      }
    );
  }
}
