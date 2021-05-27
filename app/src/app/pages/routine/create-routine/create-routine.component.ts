import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Exercise } from 'src/app/models/exercise';
import { ExercisesService } from 'src/app/services/exercises.service';
import {removeItem} from '../../../shared/utilities';
@Component({
  selector: 'bh-create-routine',
  templateUrl: './create-routine.component.html',
  styleUrls: ['./create-routine.component.css'],
})
export class CreateRoutineComponent implements OnInit {
  exercises: Exercise[];
  exercisesDrop: Exercise[]=[];
  totalExercises: number = 30;
  totalPages: number = 4;
  pageSize: number = 4;
  currentPageExercise: number = 1;

  loaded: boolean;
  error: boolean;
  nameSearched = new FormControl('');
  itemDrop: boolean[] = [true, false];
  itemsDrop:any={};

  constructor(
    private modalService: NgbModal,
    private exercisesService: ExercisesService
  ) {}

  ngOnInit(): void {
    this.fetchExercise();
  }
  private setItems=(exercises: Exercise[])=>{
    for (let index = 0; index < exercises.length; index++) {
      let id=exercises[index].idEjercicio;
      if(!this.itemsDrop[id]){
        this.itemsDrop[id]=false;
      }
    }
    console.log("ItemsDrop: ",this.itemsDrop);

  }
  private fetchExercise = () => {
    this.exercises = [];
    this.loaded = false;
    this.error = false;
    this.exercisesService
      .getExercisesByPages(
        this.currentPageExercise - 1,
        this.pageSize,
        'nombre',
        true
      )
      .subscribe(
        (data) => {
          this.totalExercises = data.totalElements;
          this.totalPages = Math.round(data.totalElements / this.pageSize);
          // this.childPagination.forEach((PaginationComponent) => {
          //   PaginationComponent.setPages(this.totalPages);
          // });
          this.loaded = true;
          this.exercises = data.content;
          for (let index = 0; index < this.exercises.length; index++) {
            this.itemsDrop[this.exercises[index].idEjercicio]=true;
          }
          console.log("ItemsDrop: ",this.itemsDrop);
        },
        (err) => {
          this.loaded = true;
          this.error = true;
          console.log('Error: ', err.message);
        }
      );
  };
  onMuscle = (muscleId: number) => {
    this.exercises = [];
    this.loaded = false;
    this.error = false;
    this.exercisesService.getExercisesByMuscle(muscleId).subscribe(
      (data) => {
        if (data.length <= 0) {
          this.error = true;
        }
        this.loaded = true;
        this.exercises = data;
        this.setItems(this.exercises);
      },
      (err) => {
        this.loaded = true;
        this.error = true;
        console.log('Error: ', err.message);
      }
    );
  };
  onSearch = () => {
    // Limitar resultados
    if (!/^\s*$/.test(this.nameSearched.value)) {
      this.exercises = [];
      this.loaded = false;
      this.error = false;
      this.exercisesService
        .getExercisesFound(this.nameSearched.value)
        .subscribe(
          (data) => {
            this.loaded = true;
            console.log(data);
            this.exercises = data;
            this.setItems(this.exercises);
          },
          (err) => {
            this.loaded = true;
            this.error = true;
            console.log('Error: ', err.message);
          }
        );
    }else{
      this.fetchExercise();
    }
  };
  // Drag and drop functions
  onDragStart = (event,exercise:Exercise) => {
    var j = JSON.stringify(exercise);
    event.dataTransfer.setData("exercise", j);
  };
  onDragOver = (event) => {
    event.preventDefault();
  };
  onDrop = (event,exercise:Exercise) => {
    if (event.dataTransfer) {
      const ex = event.dataTransfer.getData('exercise');
      const draggableElement = document.getElementById(JSON.parse(ex).idEjercicio.toString());
      const dropzone = document.getElementById('dropZone');
      dropzone.appendChild(draggableElement);
      this.exercisesDrop.push(JSON.parse(ex));
      console.log(this.exercisesDrop);

      event.dataTransfer.clearData();
    } else {
      this.itemDrop[0] = false;
      const draggableElement = document.getElementById(exercise.idEjercicio.toString());
      const dropzone = document.getElementById('dropZone');
      dropzone.appendChild(draggableElement);
    }
  };
  onCardDrop = (event, id = null) => {
    if (event.dataTransfer) {
      const ex = event.dataTransfer.getData('exercise');
      const draggableElement = document.getElementById(JSON.parse(ex).idEjercicio.toString());
      const dropzone = document.getElementById('cardZone');
      dropzone.appendChild(draggableElement);

      this.exercisesDrop=removeItem(this.exercisesDrop,(JSON.parse(ex)));
      console.log(this.exercisesDrop);

      event.dataTransfer.clearData();
    } else {
      this.itemDrop[0] = true;
      const draggableElement = document.getElementById(id);
      const dropzone = document.getElementById('cardZone');
      dropzone.appendChild(draggableElement);
    }
  };
  open = (content) => {
    this.modalService.open(content);
  };
  save = () => {
    console.log('Close');
  };
}
