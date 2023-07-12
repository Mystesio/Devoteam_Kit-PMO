import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddStepComponent } from './add-step.component';

const routes: Routes = [
  { path: '', component: AddStepComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddStepRoutingModule { }
