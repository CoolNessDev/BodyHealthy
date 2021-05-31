import { Component, OnInit } from '@angular/core';
import { Publication } from 'src/app/models/publication';
import { PublicationService } from 'src/app/services/publication.service';

@Component({
  selector: 'bh-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  publications: Publication[]=[];
  // pageable
  totalPublications: number = 10;
  totalPages: number = 4;
  pageSize: number = 4;
  currentPage: number = 1;
  constructor(private publicationService: PublicationService) { }

  ngOnInit(): void {
    this.fetchPublications();
  }
  fetchPublications() {
    this.publicationService.getPublicationsByPages(this.currentPage-1,this.pageSize,"fecha",true).subscribe(data=>{
      this.publications = data.content;
    },err=>{
      console.log("Error: ",err);
    })
  }
}
