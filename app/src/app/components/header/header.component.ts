import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Muscle } from 'src/app/models/muscle';
import { User } from 'src/app/models/user';
import { TokenService } from 'src/app/services/auth/token/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'bh-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user?: User;
  isLogged: boolean;
  butguerStatus=false;
  muscles: Muscle;
  roles: string[];
  isAdmin = false;
  activeSection: boolean[]=[false,false,false,false,false];
  constructor(private tokenService: TokenService,private router: Router,private userService: UserService) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged=true;
      let email:string = this.tokenService.getUsername();
      this.fetchUser(email);
    }
    this.butguerStatus=false

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ADMIN') {
        this.isAdmin = true;
      }
    });
    this.router.events.forEach((event) => {
      if(event instanceof NavigationEnd) {
        this.onActiveSection(event.urlAfterRedirects);
      }
    });
  }
  onActiveSection=(url:string)=>{
    if(url.includes("/home")){
      this.activeSectionReset();
      this.activeSection[0]=true;
    }else if(url.includes("/ejercicio")){
      this.activeSectionReset();
      this.activeSection[3]=true;
    }else if(url.includes("/rutinas")){
      this.activeSectionReset();
      this.activeSection[2]=true;
    }
  }
  activeSectionReset=()=>{
    this.activeSection[0]=false;
    this.activeSection[1]=false;
    this.activeSection[2]=false;
    this.activeSection[3]=false;
    this.activeSection[4]=false;
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
  fetchUser=(email:string)=>{
    this.userService.getUser(email).subscribe(data=>{
      console.log(data);
      this.user=data;
    })
  }

}
