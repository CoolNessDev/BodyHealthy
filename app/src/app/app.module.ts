import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SharedModule } from './shared/shared.module';
import { ComponentModule } from './components/component.module';
import { ExerciseModule } from './pages/exercise/exercise.module';
import { RoutineModule } from './pages/routine/routine.module';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    SharedModule,
    ExerciseModule,
    ComponentModule,
    RoutineModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
