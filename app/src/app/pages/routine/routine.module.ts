import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentModule } from 'src/app/components/component.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { RoutinesComponent } from './routines/routines.component';
import { LevelsComponent } from './levels/levels.component';
import { CreateRoutineComponent } from './create-routine/create-routine.component';



@NgModule({
  declarations: [
    RoutinesComponent,
    LevelsComponent,
    CreateRoutineComponent
  ],
  imports: [
    CommonModule,
    ComponentModule,
    SharedModule,
  ],
  exports:[
    RoutinesComponent,
    LevelsComponent,
    CreateRoutineComponent,
  ]
})
export class RoutineModule { }
