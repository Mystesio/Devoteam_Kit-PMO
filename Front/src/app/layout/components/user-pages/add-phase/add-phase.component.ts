import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Message, MessageService } from 'primeng/api';
import {  Phase } from 'src/app/_model/phase.model';
import { PhaseService } from 'src/app/_services/phase.service';

@Component({
  selector: 'app-add-phase',
  templateUrl: './add-phase.component.html',
  styleUrls: ['./add-phase.component.scss'],
  providers: [MessageService]
})
export class AddPhaseComponent {
  productDialog: boolean = false;
  phaseForm: FormGroup | undefined;

  value5: any;


  phase: Phase = {
    startDate: new Date(),
    endDate: new Date(),
    phaseId: 0,
    phaseName: '',
    project: {
      projectId: '',
      projectName: '',
      projectDescription: '',
      sponsor: '',
      domain: '',
      nature: '',
      startDate: new Date(),
      endDate: new Date(),
      phases: []
    },
    steps: []
  };
  successMessage: string = '';
  msgs: Message[] = [];
  constructor(private phaseService: PhaseService) { }


  ngOnInit(): void {

  }

  openNew() {
    this.productDialog = true;
  }


  addPhase(phaseForm: NgForm) {
    this.phaseService.addPhase(this.phase).subscribe(
      (response: Phase) => {
        console.log(response);
        this.successMessage = 'Phase added successfully!';
        phaseForm.reset();


      },
      (error: HttpErrorResponse) => {
        console.log(error)
      }
    );
  }





  hideDialog() {
    this.productDialog = false;
}


showSuccessViaMessages() {
  this.msgs = [];
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Phase added successfully!' });
}
}
