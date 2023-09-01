import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTaskComponent } from './add-task.component';
import { AddStepComponent } from '../add-step/add-step.component';


const routes: Routes = [
  { path: '', component: AddTaskComponent },
  { path: 'admin/pages/addStep', component: AddStepComponent },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddTaskRoutingModule { }
