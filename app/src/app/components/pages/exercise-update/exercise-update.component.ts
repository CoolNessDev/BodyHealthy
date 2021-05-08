import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-exercise-update',
  templateUrl: './exercise-update.component.html',
  styleUrls: ['./exercise-update.component.css']
})
export class ExerciseUpdateComponent implements OnInit {
 
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
  onUpdate(): void {
    // const id = this.activatedRoute.snapshot.params.id;
    // this.productoService.update(id, this.producto).subscribe(
    //   data => {
    //     this.toastr.success('Producto Actualizado', 'OK', {
    //       timeOut: 3000, positionClass: 'toast-top-center'
    //     });
    //     this.router.navigate(['/ejercicios']);
    //   },
    //   err => {
    //     this.toastr.error(err.error.mensaje, 'Fail', {
    //       timeOut: 3000,  positionClass: 'toast-top-center',
    //     });
    //   }
    // );
  }

}
