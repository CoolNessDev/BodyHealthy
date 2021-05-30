import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exercise } from 'src/app/models/exercise';
import { Routine } from 'src/app/models/routine';
import { TokenService } from 'src/app/services/auth/token/token.service';
import { ExercisesService } from 'src/app/services/exercises.service';
import { RoutineService } from 'src/app/services/routine.service';
import {
  removeExercise,
  removeDropElement,
  concatUniqueExercise,
} from '../../../shared/utilities';

@Component({
  selector: 'bh-create-routine',
  templateUrl: './create-routine.component.html',
  styleUrls: ['./create-routine.component.css'],
})
export class CreateRoutineComponent implements OnInit {
  newRutineForm: FormGroup;
  routine: Routine = new Routine();
  exercises: Exercise[] = [];
  exercisesDrop: Exercise[] = [];
  // html drag elements
  draggableElements: any[] = [];
  // pageable parameters
  totalExercises: number = 30;
  totalPages: number = 4;
  pageSize: number = 4;
  currentPageExercise: number = 1;
  // alerts
  loaded: boolean;
  error: boolean;
  // seacher
  nameSearched = new FormControl('');
  // validations
  roles: string[];
  isAdmin = false;
  itemsDrop: any = {};
  // DropZones
  dropzone;
  cardzone = document.getElementById('cardZone');
  constructor(
    private modalService: NgbModal,
    private exercisesService: ExercisesService,
    private tokenService: TokenService,
    private routineService: RoutineService
  ) {
    // no traer los que estan en routine
  }

  ngOnInit(): void {
    this.dropzone = document.getElementById('dropZone');
    this.cardzone = document.getElementById('cardZone');
    this.initForm();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
    this.fetchExercise();
  }
  private setItems = (exercises: Exercise[]) => {
    for (let index = 0; index < exercises.length; index++) {
      let id = exercises[index].idEjercicio;
      if (this.itemsDrop[id] == undefined) {
        this.itemsDrop[id] = true;
      }
    }
    console.log('ItemsDrop: ', this.itemsDrop);
  };
  private fetchExercise = () => {
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
          this.loaded = true;
          this.exercises = concatUniqueExercise(this.exercises, data.content);
          this.setItems(this.exercises);
          // update items droped
          this.dropItems(this.draggableElements);
        },
        (err) => {
          this.loaded = true;
          this.error = true;
          // update items droped
          this.dropItems(this.draggableElements);
          console.log('Error: ', err.message);
        }
      );
  };
  onMuscle = (muscleId: number) => {
    this.loaded = false;
    this.error = false;
    this.exercisesService.getExercisesByMuscle(muscleId).subscribe(
      (data) => {
        if (data.length <= 0) {
          this.error = true;
        }
        this.loaded = true;
        this.exercises = concatUniqueExercise(this.exercises, data);
        // this.exercises=this.exercises.reverse();
        this.setItems(this.exercises);
        // update items droped
        this.dropItems(this.draggableElements);
      },
      (err) => {
        this.loaded = true;
        this.error = true;
        // update items droped
        this.dropItems(this.draggableElements);
        console.log('Error: ', err.message);
      }
    );
  };
  onSearch = () => {
    // Limitar resultados
    if (!/^\s*$/.test(this.nameSearched.value)) {
      this.loaded = false;
      this.error = false;
      this.exercisesService
        .getExercisesFound(this.nameSearched.value)
        .subscribe(
          (data) => {
            this.loaded = true;
            console.log(data);
            this.exercises = concatUniqueExercise(this.exercises, data);
            this.setItems(this.exercises);
            // update items droped
            this.dropItems(this.draggableElements);
          },
          (err) => {
            this.loaded = true;
            this.error = true;
            // update items droped
            this.dropItems(this.draggableElements);
            console.log('Error: ', err.message);
          }
        );
    } else {
      this.fetchExercise();
    }
  };
  initForm = (): void => {
    this.newRutineForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      nivel: new FormControl(null, Validators.required),
    });
  };
  setValues() {
    this.routine.nombre = this.name.value;
    this.routine.nivel = this.nivel.value;
    this.routine.ejercicios = this.exercisesDrop;
    this.routine.userName = this.tokenService.getUsername();
  }
  private get name() {
    return this.newRutineForm.get('name');
  }
  private get nivel() {
    return this.newRutineForm.get('nivel');
  }

  // ### DRAG AND DROP FUNCTIONS ######################################
  onDragStart = (event, exercise: Exercise) => {
    var j = JSON.stringify(exercise);
    // add exercise in JSON
    event.dataTransfer.setData('exercise', j);
  };
  onDragOver = (event) => {
    event.preventDefault();
  };
  // On routine
  onDrop = (event, exercise: Exercise) => {
    if (event.dataTransfer) {
      // get exercise in JSON
      const ex = event.dataTransfer.getData('exercise');
      // console.log(ex);
      const draggableElement = document.getElementById(
        JSON.parse(ex).idEjercicio.toString()
      );
      this.exercisesDrop = removeExercise(this.exercisesDrop, JSON.parse(ex));
      this.draggableElements = removeDropElement(
        this.draggableElements,
        draggableElement
      );
      // set itemdrop false to arrow icon
      this.itemsDrop[JSON.parse(ex).idEjercicio] = false;
      // save element html and exercise
      this.draggableElements.push(draggableElement);
      this.exercisesDrop.push(JSON.parse(ex));
      // drop elements in dropzone
      this.dropItems(this.draggableElements);

      event.dataTransfer.clearData();
    } else {
      // drop by arrow button
      const draggableElement = document.getElementById(
        exercise.idEjercicio.toString()
      );
      // set itemdrop false to arrow icon
      this.itemsDrop[exercise.idEjercicio] = false;
      this.draggableElements.push(draggableElement);
      this.exercisesDrop.push(exercise);
      this.dropItems(this.draggableElements);
    }
  };
  // drop items in Routine
  dropItems = (draggableElements: any[]) => {
    this.dropzone.innerHTML = '';
    for (let index = 0; index < draggableElements.length; index++) {
      const element = draggableElements[index];
      this.dropzone.appendChild(element);
    }
  };
  // drop items in cardZone
  dropItemsCard = (draggableElements: any[]) => {
    for (let index = 0; index < draggableElements.length; index++) {
      const element = draggableElements[index];
      this.cardzone.appendChild(element);
    }
  };
  // On cards zone
  onCardDrop = (event, exercise: Exercise) => {
    if (event.dataTransfer) {
      // get exercise in JSON
      const ex = event.dataTransfer.getData('exercise');
      const draggableElement = document.getElementById(
        JSON.parse(ex).idEjercicio.toString()
      );
      // set itemdrop false to arrow icon
      this.itemsDrop[JSON.parse(ex).idEjercicio] = true;
      // add element to cardzone
      this.cardzone.appendChild(draggableElement);
      // remove element html and exercise from routine
      this.exercisesDrop = removeExercise(this.exercisesDrop, JSON.parse(ex));
      this.draggableElements = removeDropElement(
        this.draggableElements,
        draggableElement
      );
      // update items droped
      this.dropItems(this.draggableElements);
      event.dataTransfer.clearData();
    } else {
      // drop by arrow button
      const draggableElement = document.getElementById(
        exercise.idEjercicio.toString()
      );
      // add element to cardzone
      this.cardzone.appendChild(draggableElement);
      // remove element html and exercise from routine
      this.itemsDrop[exercise.idEjercicio] = true;
      this.exercisesDrop = removeExercise(this.exercisesDrop, exercise);
      this.draggableElements = removeDropElement(
        this.draggableElements,
        draggableElement
      );
      // update items droped
      this.dropItems(this.draggableElements);
    }
  };
  open = (content) => {
    this.modalService.open(content);
  };
  save = () => {
    this.setValues();
    console.log(this.routine);
    if (this.isAdmin) {
      this.routineService.postRoutineDefault(this.routine).subscribe(
        (data) => {
          console.log('Data: ', data);
        },
        (err) => {
          console.log('error: ', err);
        }
      );
    } else {
      this.routineService.postRoutine(this.routine).subscribe(
        (data) => {
          console.log('Data: ', data);
        },
        (err) => {
          console.log('error: ', err);
        }
      );
    }

    console.log('Close');
  };
}
