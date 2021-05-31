import { Component, Input, OnInit } from '@angular/core';
import { Publication } from 'src/app/models/publication';

@Component({
  selector: 'bh-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  @Input()
  publication: Publication;

  format: string = 'dd/MM/yyyy';
  constructor() { }

  ngOnInit(): void {

  }

}
