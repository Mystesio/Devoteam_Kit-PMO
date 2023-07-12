import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Message, MessageService } from 'primeng/api';
import { Step} from 'src/app/_model/step.model';
import { StepService } from 'src/app/_services/step.service';

@Component({
  selector: 'app-add-step',
  templateUrl: './add-step.component.html',
  styleUrls: ['./add-step.component.scss'],
  providers: [MessageService]
})
export class AddStepComponent {
  productDialog: boolean = false;
  stepForm: FormGroup | undefined;

  value5: any;


  step: Step = {
    Stepname: '',
    startDate: new Date(),
    endDate: new Date()
  };
  successMessage: string = '';
  msgs: Message[] = [];
  constructor(private stepService: StepService) { }


  ngOnInit(): void {

  }

  openNew() {
    this.productDialog = true;
  }


  addStep(stepForm: NgForm) {
    this.stepService.addStep(this.step).subscribe(
      (response: Step) => {
        console.log(response);
        this.successMessage = 'task added successfully!';
        stepForm.reset();


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
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Task added successfully!' });
}
}

