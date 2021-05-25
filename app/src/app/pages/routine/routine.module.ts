import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentModule } from 'src/app/components/component.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { RoutinesComponent } from './routines/routines.component';
import { LevelsComponent } from './levels/levels.component';



@NgModule({
  declarations: [
    RoutinesComponent,
    LevelsComponent
  ],
  imports: [
    CommonModule,
    ComponentModule,
    SharedModule
  ],
  exports:[
    RoutinesComponent,
    LevelsComponent
  ]
})
export class RoutineModule { }
