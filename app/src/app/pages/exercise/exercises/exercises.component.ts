import { Component, OnInit } from '@angular/core';
import { ExercisesService } from 'src/app/services/exercises.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'bh-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent implements OnInit {
  exercises!: any;
  loaded: boolean;
  error: boolean;
  constructor(private exercisesService: ExercisesService,
    private toastr: ToastrService) {
    // this.exercises = exercisesService.getExercises();
  }

  ngOnInit(): void {
    this.fetchExercise();
  }
  private  fetchExercise() {
    this.loaded=false;
    this.error=false;
    this.exercisesService.getExercises().subscribe((data)=>{
      this.loaded=true;
      this.exercises=data;
    },
    (err)=>{
      this.loaded=true;
      this.error=true;
      console.log("Error: ",err.message);
    });
  }
}
