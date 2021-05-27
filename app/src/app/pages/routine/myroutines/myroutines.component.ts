import { Component, OnInit } from '@angular/core';
import { Routine } from 'src/app/models/routine';
import { RoutineService } from 'src/app/services/routine.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'bh-myroutines',
  templateUrl: './myroutines.component.html',
  styleUrls: ['./myroutines.component.css']
})
export class MyroutinesComponent implements OnInit {
  routines?: Routine[];
  constructor(private routinesService: RoutineService, private userServices: UserService) { }

  ngOnInit(): void {
    let userId:number = parseInt(this.userServices.getUserId());
    this.fetchRoutines(userId)
  }
  fetchRoutines(userId: number) {
    console.log(userId);
    this.routinesService.getRoutinesByUser(userId).subscribe(data =>{
      console.log("Data: ",data);
      this.routines=data;
    },err=>{
      console.log(err.error.message);
    })
  }

}
