import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/auth/token/token.service';

@Component({
  selector: 'bh-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLogged: boolean;
  constructor(private tokenService: TokenService,private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.getToken){
      this.isLogged=true;
    }
  }
  onLogOut():void{
    this.tokenService.logOut();
    this.isLogged=false;
    this.router.navigate(["/"]);
  }

}
