import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';

@Component({
  selector: 'bh-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  roles: string[];
  user: User;
  error: boolean;


  constructor() { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      name: new FormControl(null,Validators.required),
      surname: new FormControl(null,Validators.required),
      username: new FormControl(null,Validators.required),
      date: new FormControl(null,Validators.required),
      password: new FormControl(null,Validators.required),
      password2: new FormControl(null,Validators.required),
    });
  }
  onRegister=()=>{

  }
  public get surname(){
    return this.registerForm.get('surname');
  }
  public get date(){
    return this.registerForm.get('date');
  }
}
