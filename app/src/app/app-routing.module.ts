import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExerciseFormComponent } from './components/pages/exercise-form/exercise-form.component';
import { ExerciseUpdateComponent } from './components/pages/exercise-update/exercise-update.component';
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
  },
  // { 
  //   path: 'editar/:id', 
  //   component: EditarProductoComponent, 
  //   canActivate: [guard], 
  //   data: { expectedRol: ['admin'] } 
  // },
  {
    path: 'ejercicios/editar',
    component: ExerciseUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }