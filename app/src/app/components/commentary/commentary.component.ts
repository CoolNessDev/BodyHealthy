import { Component, Input, OnInit } from '@angular/core';
import { Commentary } from 'src/app/models/commentary';
import { CommmentaryService } from 'src/app/services/commentary.service';
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
  constructor(
    private userService: UserService,
    private commentaryService: CommmentaryService
  ) {}

  ngOnInit(): void {
    if (
      this.commentary &&
      this.commentary.usuario.idUsuario ==
        parseInt(this.userService.getUserId())
    ) {
      this.options = true;
    }
  }
  onDelete = () => {
    this.commentaryService
      .deleteCommentary(this.commentary.idComentario)
      .subscribe(
        (data) => {
          console.log(data);
        },
        (err) => {
          console.log(err);
        }
      );
  };
}
