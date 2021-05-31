import { Component, Input, OnInit } from '@angular/core';
import { Commentary } from 'src/app/models/commentary';
import { Publication } from 'src/app/models/publication';
import { CommmentaryService } from 'src/app/services/commentary.service';

@Component({
  selector: 'bh-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  @Input()
  publication: Publication;

  commentaries: Commentary[]=[];

  format: string = 'dd/MM/yyyy';
  constructor(private commentaryService: CommmentaryService) { }

  ngOnInit(): void {
    this.fetchComentaries();
  }
  fetchComentaries() {
    this.commentaryService.getComentariesByPublication(this.publication.idPublicacion).subscribe(data=>{
      this.commentaries=data;
    },err=>{
      console.log("Error: ",err);
    })
  }

}
