import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Publication } from 'src/app/models/publication';
import { User } from 'src/app/models/user';
import { CloudinaryService } from 'src/app/services/cloudinary.service';
import { PublicationService } from 'src/app/services/publication.service';
import { UserService } from 'src/app/services/user.service';
import { getUrl } from 'src/app/shared/utilities';

@Component({
  selector: 'bh-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  // Main user
  user: User = new User();
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
    private userService: UserService,
    private cloudinaryService: CloudinaryService
  ) {}

  ngOnInit(): void {
    this.fetchPublications();
    this.publicationForm = new FormGroup({
      message: new FormControl(null, Validators.required),
    });
    this.user.idUsuario = parseInt(this.userService.getUserId());
    this.user.nombres = this.userService.getNames();
    this.user.imagen = this.userService.getUserImg();
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
          this.publications.map(i=>{
            if(i.imagen){
              return i.imagen=getUrl(i.imagen)
            }
          })
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
  onUpdate() {
    this.publicationService.postPublication(this.publication).subscribe(
      (data) => {
        console.log(data);
      },
      (err) => {
        console.log(err);
      }
    );
  }
  onPublicate = () => {
    this.publication.fecha = new Date();
    this.publication.mensaje = this.message.value;
    this.publication.usuario = this.user;
    if (this.newImg) {
      this.cloudinaryService.uploadImage(this.imagen).subscribe(
        (data) => {
          console.log('Imagen subida: ', data.message);
          this.publication.imagen = data.message;
          this.onUpdate();
        },
        (err) => {
          alert(err.error.mensaje);
          // this.spinner.hide();
          this.reset();
        }
      );
    } else {
      this.onUpdate();
    }
    console.log(this.publication);
  };
  private get message() {
    return this.publicationForm.get('message');
  }
}
