import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPhaseComponent } from './add-phase.component';

const routes: Routes = [
  { path: '', component: AddPhaseComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddPhaseRoutingModule { }
