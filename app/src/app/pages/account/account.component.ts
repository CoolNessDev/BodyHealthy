import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'bh-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  registerForm: FormGroup;
  roles: string[];
  user: User;
  error: boolean;

  constructor() {
    this.registerForm = new FormGroup({
      name: new FormControl(null,Validators.required),
      surname: new FormControl(null,Validators.required),
      username: new FormControl(null,Validators.required),
      date: new FormControl(null,Validators.required),
      password: new FormControl(null,Validators.required),
      password2: new FormControl(null,Validators.required),
    });
   }

  ngOnInit(): void {
  }
  onRegister=()=>{

  }

}
