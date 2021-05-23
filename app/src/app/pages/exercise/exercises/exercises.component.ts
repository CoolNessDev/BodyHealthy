import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.css']
})
export class ExercisesComponent implements OnInit {
  exercises!: any;
  loaded: boolean;
  error: boolean;
  nameSearched = new FormControl('');

  currentPageExercise:number=1;
  constructor(private exercisesService: ExercisesService) {}

  ngOnInit(): void {
    this.fetchExercise();
  }
  private  fetchExercise=()=> {
    this.loaded=false;
    this.error=false;
    this.exercisesService.getExercisesByPages(this.currentPageExercise-1,4,"nombre",true).subscribe((data)=>{
      this.loaded=true;
      this.exercises=data.content;
    },
    (err)=>{
      this.loaded=true;
      this.error=true;
      console.log("Error: ",err.message);
    });
  }
  receivePage=($event)=> {
    if($event!==0){
      this.exercises=[]
      this.loaded=false;
      this.currentPageExercise=$event;
      this.fetchExercise();
    }
  }
  onSearch=()=>{
    // Limitar resultados
    this.exercises=[]
    this.loaded=false;
    this.error=false;
    this.exercisesService.getExercisesFound(this.nameSearched.value).subscribe((data)=>{
      this.loaded=true;
      console.log(data);
      this.exercises=data;
    },
    (err)=>{
      this.loaded=true;
      this.error=true;
      console.log("Error: ",err.message);
    });
  }
}
