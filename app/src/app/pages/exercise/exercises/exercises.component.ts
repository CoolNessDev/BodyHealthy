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
  constructor(private exercisesService: ExercisesService,
    private toastr: ToastrService) {
    // this.exercises = exercisesService.getExercises();
  }

  ngOnInit(): void {
    this.fetchExercise();
  }
  private  fetchExercise() {
    this.exercisesService.getExercises().subscribe((data)=>{
      this.exercises=data;
    });
  }
}
