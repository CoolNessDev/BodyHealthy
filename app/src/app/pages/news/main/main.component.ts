import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Publication } from 'src/app/models/publication';
import { User } from 'src/app/models/user';
import { PublicationService } from 'src/app/services/publication.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'bh-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  // Main user
  user: User=new User();
  // publication
  publication: Publication = new Publication();
  publicationForm: FormGroup;
  publications: Publication[] = [];
  // img
  imagen: File;
  imagenSrc: File;
  newImg: boolean = false;
  // pageable
  totalPublications: number = 10;
  totalPages: number = 4;
  pageSize: number = 4;
  currentPage: number = 1;
  constructor(
    private publicationService: PublicationService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchPublications();
    this.publicationForm = new FormGroup({
      message: new FormControl(null, Validators.required),
    });
    this.user.idUsuario = parseInt(this.userService.getUserId());
    this.user.nombres= this.userService.getNames();
    this.user.imagen= this.userService.getUserImg();
  }
  fetchPublications() {
    this.publicationService
      .getPublicationsByPages(
        this.currentPage - 1,
        this.pageSize,
        'fecha',
        true
      )
      .subscribe(
        (data) => {
          this.publications = data.content;
        },
        (err) => {
          console.log('Error: ', err);
        }
      );
  }
  onFileChange(event) {
    this.imagen = event.target.files[0];
    const fr = new FileReader();
    fr.onload = (evento: any) => {
      this.newImg = true;
      this.imagenSrc = evento.target.result;
    };
    fr.readAsDataURL(this.imagen);
  }
  reset(): void {
    this.imagen = null;
    this.imagenSrc = null;
  }
  onPublicate = () => {};
  private get message() {
    return this.publicationForm.get('message');
  }
}
