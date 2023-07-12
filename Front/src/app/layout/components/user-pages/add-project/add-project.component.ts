import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Message, MessageService } from 'primeng/api';
import { Project } from 'src/app/_model/project.model';
import { ProjectService } from 'src/app/_services/project.service';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.scss'],
  providers: [MessageService]
})
export class AddProjectComponent {
  productDialog: boolean = false;
  projectForm: FormGroup | undefined;

  value5: any;


  project: Project = {
    projectName: "",
    projectDescription: "",
    sponsor: "",
    domain: "",
    nature: "",
    startDate: new Date(),
    endDate: new Date(),
    projectId: "",
    phases: []
  };
  successMessage: string = '';
  msgs: Message[] = [];
  constructor(private projectService: ProjectService) { }


  ngOnInit(): void {

  }

  openNew() {
    this.productDialog = true;
  }


  addProject(projectForm: NgForm) {
    this.projectService.addProject(this.project).subscribe(
      (response: Project) => {
        console.log(response);
        this.successMessage = 'Project added successfully!';
        projectForm.reset();


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
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Project added successfully!' });
}
}
