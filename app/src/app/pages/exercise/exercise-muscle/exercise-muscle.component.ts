import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'bh-exercise-muscle',
  templateUrl: './exercise-muscle.component.html',
  styleUrls: ['./exercise-muscle.component.css']
})
export class ExerciseMuscleComponent implements OnInit {




  constructor() { }

  ngOnInit(): void {
  }

  onFocus(){
    console.log("Focus");
    window.location.hash = '#myerr';
  }

}
