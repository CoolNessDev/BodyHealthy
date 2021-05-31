import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Routine } from 'src/app/models/routine';
import { RoutineService } from 'src/app/services/routine.service';

@Component({
  selector: 'bh-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css'],
})
export class LevelsComponent implements OnInit {
  routines: Routine[];
  constructor(
    private routineService: RoutineService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let level: string = this.activatedRoute.snapshot.params.level;
    this.fetchRoutines(level);
  }
  fetchRoutines(level: string) {
    this.routineService.getDefaultRoutinesByLevel(level).subscribe(
      (data) => {
        this.routines = data;
      },
      (err) => {
        console.log('Error: ', err);
        window.location.href="";
      }
    );
  }
}
