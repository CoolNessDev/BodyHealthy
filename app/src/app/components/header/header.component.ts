import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Muscle } from 'src/app/models/muscle';
import { TokenService } from 'src/app/services/auth/token/token.service';
import { MuscleService } from 'src/app/services/muscle.service';

@Component({
  selector: 'bh-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogged: boolean;
  butguerStatus=false;
  muscles: Muscle;
  roles: string[];
  isAdmin = false;
  constructor(private tokenService: TokenService,private router: Router,private muscleService: MuscleService) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged=true;
    }
    this.butguerStatus=false

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
    // this.muscleService.getAllMuscles().subscribe(data=>{
    //   this.muscles=data;
    //   console.log("Musculos:", data);

    // },error=>{
    //   console.log("Error: ",error);

    // }
    // )
  }
  onLogOut():void{
    this.tokenService.logOut();
    this.isLogged=false;
    this.router.navigate(["/"]);
    window.location.reload()
  }
  onBurguerClick(){
    this.butguerStatus=!this.butguerStatus;
  }

}
