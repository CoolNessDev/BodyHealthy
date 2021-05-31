import { Component, Input, OnInit } from '@angular/core';
import { Commentary } from 'src/app/models/commentary';

@Component({
  selector: 'bh-commentary',
  templateUrl: './commentary.component.html',
  styleUrls: ['./commentary.component.css']
})
export class CommentaryComponent implements OnInit {
  @Input()
  commentary: Commentary;
  format: string = 'dd/MM/yyyy';
  constructor() { }

  ngOnInit(): void {
  }

}
