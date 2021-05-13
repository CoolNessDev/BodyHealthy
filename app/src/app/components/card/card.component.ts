import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input()
  exercise!: any;
  constructor(private exercisesService: ExercisesService,
    private toastr: ToastrService,private router: Router ) { }

  ngOnInit(){
    console.log('ejercicios2: ',this.exercise);
    
  }
  borrar(id: number) {
    console.log("e1: ", id);
    
    this.exercisesService.delete(id).subscribe(
      data => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}
