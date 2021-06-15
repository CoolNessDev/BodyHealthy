import { Component, Input, OnInit } from '@angular/core';
import { Commentary } from 'src/app/models/commentary';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'bh-commentary',
  templateUrl: './commentary.component.html',
  styleUrls: ['./commentary.component.css'],
})
export class CommentaryComponent implements OnInit {
  @Input()
  commentary: Commentary;
  format: string = 'dd/MM/yyyy';

  options: boolean = false;
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    if (
      this.commentary &&
      this.commentary.usuario.idUsuario ==
        parseInt(this.userService.getUserId())
    ) {
      this.options = true;
    }
  }
}
