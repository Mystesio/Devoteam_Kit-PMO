import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPhaseComponent } from './add-phase.component';
import { AddProjectComponent } from '../add-project/add-project.component';

const routes: Routes = [
  { path: '', component: AddPhaseComponent },
  { path: 'admin/pages/addProject', component: AddProjectComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddPhaseRoutingModule { }
