import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from 'src/app/services/auth/token/token.service';
import { ExercisesService } from 'src/app/services/exercises.service';

@Component({
  selector: 'bh-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent implements OnInit {
  @Input()
  exercise!: any;

  roles: string[];
  isAdmin = false;
  constructor(
    private exercisesService: ExercisesService,
    private toastr: ToastrService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  ngOnInit() {
    //quitar id de la imagen en la url
    this.exercise.imagen = this.getUrl(this.exercise.imagen);

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
  }
  borrar(id: number) {
    console.log('e1: ', id);

    this.exercisesService.delete(id).subscribe(
      (data) => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      },
      (err) => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
      }
    );
  }
  getUrl(img: String): String {
    if (img.includes(':-:')) {
      let len = img.length;
      let substr = img.substring(img.indexOf(':-:'), len);
      return img.replace(substr, '');
    } else {
      return img;
    }
  }
}
