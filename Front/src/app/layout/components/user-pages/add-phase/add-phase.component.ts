import { HttpErrorResponse, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'primeng/api';
import {  Phase } from 'src/app/_model/phase.model';
import { Project } from 'src/app/_model/project.model';
import { PhaseService } from 'src/app/_services/phase.service';



@Component({
  selector: 'app-add-phase',
  templateUrl: './add-phase.component.html',
  styleUrls: ['./add-phase.component.scss'],
  providers: [MessageService]
})
export class AddPhaseComponent {
  productDialog = false;
  phaseDialog = false;
  phaseForm: FormGroup | undefined;
  updateForm: FormGroup | undefined;
  value5: any;
  msgs: Message[] = [];
  phases: Phase[] = [];
  project : Project= {
    projectId: '',
    projectName: '',
    projectDescription: '',
    sponsor: '',
    domain: '',
    nature: '',
    startDate: new Date(),
    endDate:new Date(),
  };
  phase: Phase = {
    phaseId: 0,
    phaseName: '',
    startDate: new Date(),
    endDate: new Date(),
    
  };
  successMessage: string = '';



  
  constructor(private phaseService: PhaseService, @Inject(ActivatedRoute) private route: ActivatedRoute) { 
    this.loadPhases();
  }
  
  loadPhases() {
      this.phaseService.getAllPhases(this.project).subscribe(
        (phases: Phase[]) => {
          this.phases = phases;
        },
        (error) => {
          console.error('Error loading phases:', error);
        }
      );
    }

    ngOnInit() {
      this.route.queryParams.subscribe(params => {
        this.project = JSON.parse(params['project']);
      });
     
    }

  openNew() {
    this.productDialog = true;
  }


  
  addPhase(phaseForm: NgForm) {
  
    this.phaseService.addPhase(this.phase, this.project).subscribe(
      (response: Phase) => {
        console.log(response);
        this.successMessage = 'Phase added successfully!';
        phaseForm.reset();
        window.location.reload();
      
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  onDeletePhase(phase: Phase) {
    this.phaseService.deletePhase(phase).subscribe(
      () => {
        console.log('Phase deleted successfully.');
        this.ngOnInit();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.error('An error occurred while deleting the project:', error);
      }
    );
  }
  
  onUpdatePhase(updateForm: NgForm) {
    this.phaseService.updatePhase(this.phase).subscribe(
      (updatedPhase) => {
        console.log('Phase updated successfully:', updatedPhase);
        updateForm.reset();
        this.hideDialog();
        window.location.reload();
 
      },
      (error) => {
        console.error('An error occurred while updating the project:', error);
      }
    );
  }


  openPhase(phase: Phase) {
    this.phase = { ...phase }; // Make a copy of the project to avoid modifying the original object
    this.phaseDialog = true;
  }


  hideDialog() {
    this.productDialog = false;
}


showSuccessViaMessages() {
  this.msgs = [];
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Phase added successfully!' });
}

}