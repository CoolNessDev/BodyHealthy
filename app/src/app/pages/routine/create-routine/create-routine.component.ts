import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Exercise } from 'src/app/models/exercise';
@Component({
  selector: 'bh-create-routine',
  templateUrl: './create-routine.component.html',
  styleUrls: ['./create-routine.component.css'],
})
export class CreateRoutineComponent implements OnInit {
  exercises: Exercise[];
  itemDrop: boolean[]=[true,false];
  constructor(private modalService: NgbModal) {}

  ngOnInit(): void {}
  onDragStart = (event) => {
    event.dataTransfer.setData('text/plain', event.target.id);
  };
  onDragOver = (event) => {
    event.preventDefault();
  };
  onDrop = (event, id=null) => {
    if (event.dataTransfer) {
      const id1 = event.dataTransfer.getData('text');
      const draggableElement = document.getElementById(id1);
      const dropzone = document.getElementById('dropZone');
      // const dropzone = event.target;
      dropzone.appendChild(draggableElement);
      event.dataTransfer.clearData();
    }else{
      this.itemDrop[0]=false;
      const draggableElement = document.getElementById(id);
      const dropzone = document.getElementById('dropZone');
      dropzone.appendChild(draggableElement);
    }

  };
  onCardDrop = (event, id=null) => {
    if (event.dataTransfer) {
      const id1 = event.dataTransfer.getData('text');
      const draggableElement = document.getElementById(id1);
      const dropzone = document.getElementById('cardZone');
      dropzone.appendChild(draggableElement);
      event.dataTransfer.clearData();
    }else{
      this.itemDrop[0]=true;
      const draggableElement = document.getElementById(id);
      const dropzone = document.getElementById('cardZone');
      dropzone.appendChild(draggableElement);
    }

  };
  open=(content)=> {
    this.modalService.open(content);
  }
  save = () => {
    console.log('Close');
  };
}
