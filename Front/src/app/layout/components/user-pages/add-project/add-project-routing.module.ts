import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProjectComponent } from './add-project.component';
import { AddPhaseComponent } from '../add-phase/add-phase.component';




const routes: Routes = [
  { path: '', component: AddProjectComponent },
  { path: 'admin/pages/addPhase', component: AddPhaseComponent },



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddProjectRoutingModule { }
