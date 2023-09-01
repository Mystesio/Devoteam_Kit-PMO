import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStepComponent } from './add-step.component';
import { AddPhaseComponent } from '../add-phase/add-phase.component';
import { AddTaskComponent } from '../add-task/add-task.component';

const routes: Routes = [
  { path: '', component: AddStepComponent },
  { path: 'admin/pages/addPhase', component: AddPhaseComponent },
  { path: 'admin/pages/addTask', component: AddTaskComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddStepRoutingModule { }
