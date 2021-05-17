import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Exercise } from 'src/app/models/exercise';
import { ExercisesService } from 'src/app/services/exercises.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
@Component({
  selector: 'bh-exercise-form',
  templateUrl: './exercise-form.component.html',
  styleUrls: ['./exercise-form.component.css']
})
export class ExerciseFormComponent implements OnInit {

  imagen: File;
  imagenMin: File;

  name: string = '';
  duration: number = null;
  series: number = null;
  repetitions: number = null;
  img: string = 'https://i.ibb.co/1dKwX7p/plancha.jpg';
  description: string = 'asd';
  break1: number = null;

  @ViewChild('rangoR', { static: false })
  rangoR!: ElementRef;
  private _textoRangoR: string = '2';
  constructor(private exercisesService: ExercisesService,
    private toastr: ToastrService,
    private router: Router,
    private spinner: NgxSpinnerService ) { }

  set textoRangoR(t: any) {
    this._textoRangoR = t;
  }
  get textoRangoR() {
    return this._textoRangoR;
  }
  ngOnInit(): void {

  }
  onFileChange(event) {
    this.imagen = event.target.files[0];
    const fr = new FileReader();
    fr.onload = (evento: any) => {
      this.imagenMin = evento.target.result;
    };
    fr.readAsDataURL(this.imagen);
  }
  onCreate(): void {
    console.log("Description: ",this.description);

    const exercise = new Exercise(this.name, this.duration,this.series,this.repetitions,
      this.img,this.description,this.break1);
    this.exercisesService.save(exercise).subscribe(
      data => {
        this.toastr.success('Producto Creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/ejercicios']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        // this.router.navigate(['/']);
      }
    );
  }
  onUpload(): void {
    this.spinner.show();
    this.exercisesService.uploadImage(this.imagen).subscribe(
      data => {
        this.spinner.hide();
        // this.router.navigate(['/']);
        console.log("Subido: ", data.message);
        this.img=data.message;
        this.onCreate()

      },
      err => {
        alert(err.error.mensaje);
        this.spinner.hide();
        this.reset();
      }
    );
    // this.reset();
    this.spinner.hide();
  }

  reset(): void {
    this.imagen = null;
    this.imagenMin = null;
    // this.imagenFile.nativeElement.value = '';
  }
}
