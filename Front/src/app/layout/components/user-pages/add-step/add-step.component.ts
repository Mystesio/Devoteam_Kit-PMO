import { HttpErrorResponse ,HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Message, MessageService } from 'primeng/api';
import { Phase } from 'src/app/_model/phase.model';
import { Step} from 'src/app/_model/step.model';
import { StepService } from 'src/app/_services/step.service';

@Component({
  selector: 'app-add-step',
  templateUrl: './add-step.component.html',
  styleUrls: ['./add-step.component.scss'],
  providers: [MessageService]
})
export class AddStepComponent {
  productDialog = false;
  stepDialog = false;
  stepForm: FormGroup | undefined;
  updateForm: FormGroup | undefined;
  value5: any;
  msgs: Message[] = [];
  steps: Step[] = [];
  phase: Phase = {
    phaseId: 0,
    phaseName: '',
    startDate: new Date(),
    endDate: new Date()
  };
  step: Step = {
    stepId: '',
    stepName: '',
    startDate: new Date(),
    endDate: new Date(),
  };
  successMessage: string = '';



  
  constructor(private stepService: StepService, private router: Router, private route: ActivatedRoute) { 
    this.loadSteps();
  }

  
  loadSteps() {
      this.ngOnInit();
      this.stepService.getAllSteps(this.phase).subscribe(
        (steps: Step[]) => {
          this.steps = steps;
        },
        (error) => {
          console.error('Error loading steps:', error);
        }
      );
    }

    ngOnInit() {
      this.route.queryParams.subscribe(params => {
        this.phase = JSON.parse(params['phase']);
      });
     
    }

  openNew() {
    this.productDialog = true;
  }


  addStep(stepForm: NgForm) {
    this.ngOnInit();
    this.stepService.addStep(this.step, this.phase).subscribe(
      (response:Step) => {
        console.log(response);
        this.successMessage = 'step added successfully!';
        stepForm.reset();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error)
      }
    );
  }

  onDeleteStep(step: Step) {
    this.ngOnInit();
    this.stepService.deleteStep(step).subscribe(
      () => {
        console.log('Step deleted successfully.');
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.error('An error occurred while deleting the step:', error);
      }
    );
  }
  
  onUpdateStep(updateForm: NgForm) {
    this.ngOnInit();
    this.stepService.updateStep(this.step).subscribe(
      (updatedStep) => {
        console.log('Step updated successfully:', updatedStep);
        updateForm.reset();
        this.hideDialog();
        window.location.reload();
 
      },
      (error) => {
        console.error('An error occurred while updating the step:', error);
      }
    );
  }


  openStep(step: Step) {
    this.step = { ...step }; 
    this.stepDialog = true;
  }


  hideDialog() {
    this.productDialog = false;
}


showSuccessViaMessages() {
  this.msgs = [];
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Step added successfully!' });
}

GetStep(step: Step) {
  this.stepService.getStep(step).subscribe(
    (step: Step) => {
      this.step = step;
      this.router.navigate(['/admin/pages/addTask'],{queryParams: { step: JSON.stringify(this.step) }})
    },
    (error: any) => {
      console.error('Error loading step:', error);
      
    }
  );
}
}

