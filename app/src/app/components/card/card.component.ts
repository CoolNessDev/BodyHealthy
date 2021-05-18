import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
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
    private tokenService: TokenService,
    private spinner: NgxSpinnerService,
    private router: Router
  ) {}

  ngOnInit() {
    //quitar id de la imagen en la url
    // this.exercise.imagen = this.getUrl(this.exercise.imagen);

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
  }
  borrar(id: number) {
    console.log('e1: ', id);
    this.spinner.show();
    this.exercisesService.delete(id).subscribe(
      (data) => {
        const imgId = this.getImageId(this.exercise.imagen);
        if (imgId != 'vacio') {
          this.exercisesService.deleteImage(imgId).subscribe(
            (data) => {
              console.log('eliminado');
            },
            (err) => {
              console.log('Error: ', err.meesage);
            }
          );
        }
        this.spinner.hide();
        window.location.reload();
      },
      (err) => {
        this.spinner.hide();
        console.log('Error: ', err.meesage);
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
  getImageId(img: String): String {
    if (img.includes(':-:')) {
      let substr = img.substring(0, img.indexOf(':-:'));
      return img.replace(substr + ':-:', '');
    } else {
      return 'vacio';
    }
  }
}
