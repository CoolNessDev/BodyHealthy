import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exercise } from 'src/app/models/exercise';
import { Routine } from 'src/app/models/routine';
import { TokenService } from 'src/app/services/auth/token/token.service';
import { ExercisesService } from 'src/app/services/exercises.service';
import { RoutineService } from 'src/app/services/routine.service';
import { removeExercise } from '../../../shared/utilities';

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

  // pageable parameters
  totalExercises: number = 30;
  totalPages: number = 3;
  pageSize: number = 6;
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

  constructor(
    private modalService: NgbModal,
    private exercisesService: ExercisesService,
    private tokenService: TokenService,
    private routineService: RoutineService
  ) {}

  ngOnInit(): void {
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
  };
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
          this.loaded = true;
          this.exercises = data.content;
          this.setItems(this.exercises);
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
    this.exercises = [];
    if (!/^\s*$/.test(this.nameSearched.value)) {
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

      this.exercisesDrop = removeExercise(this.exercisesDrop, JSON.parse(ex));

      // set itemdrop false to arrow icon
      this.itemsDrop[JSON.parse(ex).idEjercicio] = false;
      // save element html and exercise

      this.exercisesDrop.push(JSON.parse(ex));

      event.dataTransfer.clearData();
    } else {
      // drop by arrow button
      // set itemdrop false to arrow icon
      this.itemsDrop[exercise.idEjercicio] = false;
      this.exercisesDrop.push(exercise);
    }
  };
  // On cards zone
  onCardDrop = (event, exercise: Exercise) => {
    if (event.dataTransfer) {
      // get exercise in JSON
      const ex = event.dataTransfer.getData('exercise');

      // set itemdrop false to arrow icon
      this.itemsDrop[JSON.parse(ex).idEjercicio] = true;

      // remove exercise from routine
      this.exercisesDrop = removeExercise(this.exercisesDrop, JSON.parse(ex));

      event.dataTransfer.clearData();
    } else {
      // drop by arrow button
      // remove exercise from routine
      this.itemsDrop[exercise.idEjercicio] = true;
      this.exercisesDrop = removeExercise(this.exercisesDrop, exercise);
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
