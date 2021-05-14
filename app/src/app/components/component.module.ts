import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { MainModalComponent } from './main-modal/main-modal.component';
import { CardComponent } from './card/card.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    HeaderComponent,
    MainModalComponent,
    CardComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports:[
    HeaderComponent,
    MainModalComponent,
    CardComponent
  ]
})
export class ComponentModule {}
