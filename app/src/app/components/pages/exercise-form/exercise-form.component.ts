import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Exercise } from 'src/app/models/exercise';
import { ExercisesService } from 'src/app/services/exercises.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
@Component({
  selector: 'bh-exercise-form',
  templateUrl: './exercise-form.component.html',
  styleUrls: ['./exercise-form.component.css']
})
export class ExerciseFormComponent implements OnInit {

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
    private router: Router ) { }

  set textoRangoR(t: any) {
    this._textoRangoR = t;
  }
  get textoRangoR() {
    return this._textoRangoR;
  }
  ngOnInit(): void {
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
}
