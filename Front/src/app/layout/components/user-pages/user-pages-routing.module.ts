import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'addProject', loadChildren: () => import('./add-project/add-project.module').then(m => m.AddProjectModule) },
  { path: 'addPhase', loadChildren: () => import('./add-phase/add-phase.module').then(m => m.AddPhaseModule) },
  { path: 'addStep', loadChildren: () => import('./add-step/add-step.module').then(m => m.AddStepModule) },
  { path: 'addTask', loadChildren: () => import('./add-task/add-task.module').then(m => m.AddTaskModule) }



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserPagesRoutingModule { }
