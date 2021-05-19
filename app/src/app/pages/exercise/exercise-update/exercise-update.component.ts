import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Exercise } from 'src/app/models/exercise';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-exercise-update',
  templateUrl: './exercise-update.component.html',
  styleUrls: ['./exercise-update.component.css'],
})
export class ExerciseUpdateComponent implements OnInit {
  exercise: Exercise;
  imagen: File;
  imagenMin: File;

  exerciseUpdateForm: FormGroup;
  img: string = 'https://i.ibb.co/1dKwX7p/plancha.jpg';

  @ViewChild('rangoR', { static: false })
  rangoR!: ElementRef;
  private _textoRangoR: string = '2';
  constructor(
    private exercisesService: ExercisesService,
    private toastr: ToastrService,
    private router: Router,
    private spinner: NgxSpinnerService,
    private activatedRoute: ActivatedRoute
  ) {}

  set textoRangoR(t: any) {
    this._textoRangoR = t;
  }
  get textoRangoR() {
    return this._textoRangoR;
  }
  ngOnInit(): void {
    this.spinner.show();
    this.initForm();
    const id = this.activatedRoute.snapshot.params.id;
    this.exercisesService.detail(id).subscribe(
      (data) => {
        this.spinner.hide();
        this.exercise = data;
        this.loadData(data);
        console.log(data);
        console.log(this.name.value);

      },
      (err) => {
        this.spinner.hide();
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }
  onUpdate(): void {
    this.setValues();
    const id = this.activatedRoute.snapshot.params.id;
    this.exercisesService.update(id, this.exercise).subscribe(
      data => {
        this.toastr.success('Producto Actualizado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/ejercicios']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }
  onFileChange(event) {
    this.imagen = event.target.files[0];
    const fr = new FileReader();
    fr.onload = (evento: any) => {
      this.imagenMin = evento.target.result;
    };
    fr.readAsDataURL(this.imagen);
  }
  initForm = (): void => {
    this.exerciseUpdateForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      duracion: new FormControl(null, Validators.required),
      series: new FormControl(null, Validators.required),
      repeticiones: new FormControl(null, Validators.required),
      descripcion: new FormControl(null, [
        Validators.required,
        Validators.minLength(10),
      ]),
      descanso: new FormControl(null),
    });
  };
  loadData = (data): void => {
    this.exerciseUpdateForm.get('name').setValue(data.nombre);
    this.exerciseUpdateForm.get('duracion').setValue(data.duracion);
    this.exerciseUpdateForm.get('series').setValue(data.series);
    this.exerciseUpdateForm.get('repeticiones').setValue(data.repeticiones);
    this.exerciseUpdateForm.get('descripcion').setValue(data.descripcion);
    this.exerciseUpdateForm.get('descanso').setValue(data.descanso);
  };
  setValues(){
    this.exercise.nombre=this.name.value;
    this.exercise.duracion=this.duracion.value;
    this.exercise.series=this.series.value;
    this.exercise.repeticiones=this.repeticiones.value;
    this.exercise.descripcion=this.descripcion.value;
    this.exercise.descanso=this.series.value;
  }

  private get name(){
    return this.exerciseUpdateForm.get('name');
  }
  private get duracion(){
    return this.exerciseUpdateForm.get('duracion');
  }
  private get series(){
    return this.exerciseUpdateForm.get('series');
  }
  private get repeticiones(){
    return this.exerciseUpdateForm.get('repeticiones');
  }
  private get descripcion(){
    return this.exerciseUpdateForm.get('descripcion');
  }
  private get descanso(){
    return this.exerciseUpdateForm.get('descanso');
  }

}
