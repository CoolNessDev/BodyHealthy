import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExerciseFormComponent } from './pages/exercise/exercise-form/exercise-form.component';
import { ExerciseUpdateComponent } from './pages/exercise/exercise-update/exercise-update.component';
import { ExercisesComponent } from './pages/exercise/exercises/exercises.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { GuardService as guard } from './services/guard/guard.service';
const routes: Routes = [

  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'ejercicios', component: ExercisesComponent },
  { path: 'ejercicios/insertar', component: ExerciseFormComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'ejercicios/editar/:id', component: ExerciseUpdateComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  {
    path: '**',
    redirectTo: 'home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
