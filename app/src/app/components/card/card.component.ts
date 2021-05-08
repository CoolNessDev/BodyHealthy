import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'bh-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input()
  exercise!: any;
  constructor() { }

  ngOnInit(){
    console.log('ejercicios2: ',this.exercise);
    
  }

}
