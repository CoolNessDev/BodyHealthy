import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExerciseFormComponent } from './components/pages/exercise-form/exercise-form.component';
import { ExercisesComponent } from './components/pages/exercises/exercises.component';
import { LoginComponent } from './components/pages/login/login.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'ejercicios',
    component: ExercisesComponent
  },
  {
    path: 'ejercicios/insertar',
    component: ExerciseFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
