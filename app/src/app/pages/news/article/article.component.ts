import { Component, Input, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Commentary } from 'src/app/models/commentary';
import { Publication } from 'src/app/models/publication';
import { CommmentaryService } from 'src/app/services/commentary.service';
import { PublicationService } from 'src/app/services/publication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'bh-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css'],
})
export class ArticleComponent implements OnInit {
  spinnerMessage: string = 'Actualizando';
  @Input()
  publication: Publication;

  options: boolean = false;

  commentaries: Commentary[] = [];

  format: string = 'dd/MM/yyyy';
  constructor(
    private commentaryService: CommmentaryService,
    private publicationService: PublicationService,
    private spinner: NgxSpinnerService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchComentaries();
    if (
      this.publication &&
      this.publication.usuario.idUsuario ==
        parseInt(this.userService.getUserId())
    ) {
      this.options=true
    }
  }
  fetchComentaries() {
    this.commentaryService
      .getComentariesByPublication(this.publication.idPublicacion)
      .subscribe(
        (data) => {
          this.commentaries = data;
        },
        (err) => {
          if (err.status != 404) {
            console.log('Error: ', err);
          }
        }
      );
  }
  onDelete = () => {
    this.spinnerMessage = 'Eliminando publicaión';
    this.spinner.show();
    this.publicationService
      .deletePublication(this.publication.idPublicacion)
      .subscribe(
        (data) => {
          console.log(data);
          this.spinner.hide();
          window.location.reload();
        },
        (err) => {
          this.spinner.hide();
          console.log(err);
        }
      );
  };
}
