import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
export class AddProjectComponent implements OnInit, AfterViewInit {
  productDialog: boolean = false;
  projectDialog: boolean = false;
  projectForm: FormGroup | undefined;
  updateForm: FormGroup | undefined;
  projects!: Project[];
  value5: any;
  project: Project = {
    projectId: '',
    projectName: "",
    projectDescription: "",
    sponsor: "",
    domain: "",
    nature: "",
    startDate: new Date(),
    endDate: new Date(),
    phases: []
  };
  successMessage: string = '';
  msgs: Message[] = [];

  constructor(private projectService: ProjectService, private router: Router) {
    this.loadProjects();
  }

  ngAfterViewInit() {
    setTimeout(() => {
      if (this.updateForm) {
        this.updateForm.markAsUntouched();
      }
    });

  }
  ngOnInit(): void {
  }

  openNew() {
    this.productDialog = true;
  }

  onDeleteProject(project: Project) {
    this.projectService.deleteProject(project).subscribe(
      () => {
        console.log('Project deleted successfully.');
        this.ngOnInit();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.error('An error occurred while deleting the project:', error);
      }
    );
  }

  addProject(projectForm: NgForm) {
 
    this.projectService.addProject(this.project).subscribe(
      (response: Project) => {
        console.log(response);
        this.successMessage = 'Project added successfully!';
        projectForm.resetForm();
        this.productDialog=false;
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.productDialog=false;
        window.location.reload();
      }
    );
  }
  
  
  
  onUpdateProject(updateForm: NgForm) {
    this.projectService.updateProject(this.project).subscribe(
      (updatedProject) => {
        console.log('Project updated successfully:', updatedProject);
        updateForm.reset();
        this.hideDialog();
        window.location.reload();
 
      },
      (error) => {
        console.error('An error occurred while updating the project:', error);
      }
    );
  }
  

  openProject(project: Project) {
    this.project = { ...project }; // Make a copy of the project to avoid modifying the original object
    this.projectDialog = true;
  }

  hideDialog() {
    this.projectDialog = false;
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe(
      (projects: Project[]) => {
        this.projects = projects;
      },
      (error) => {
        console.error('Error loading projects:', error);
      }
    );
  }

  GetProject(project: Project) {
    this.projectService.getProject(project).subscribe(
      (project: Project) => {
        this.project = project;
        this.router.navigate(['/admin/pages/addPhase'],{queryParams: { project: JSON.stringify(this.project) }})
      },
      (error: any) => {
        console.error('Error loading project:', error);
        
      }
    );
  }
  showSuccessViaMessages() {
    this.msgs = [];
    this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Project added successfully!' });
  }
}

