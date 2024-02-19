import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Message, MessageService } from 'primeng/api';
import { Step } from 'src/app/_model/step.model';
import { Task} from 'src/app/_model/task.model';
import { TaskService } from 'src/app/_services/task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss'],
  providers: [MessageService]
})
export class AddTaskComponent {
  productDialog = false;
  taskDialog = false;
  taskForm: FormGroup | undefined;
  updateForm: FormGroup | undefined;
  value5: any;
  msgs: Message[] = [];
  tasks: Task[] = [];
  step: Step = {
    startDate: new Date(),
    endDate: new Date(),
    stepId: '',
    stepName: ''
  };
  task: Task = {
    taskId: '',
    taskName: '',
    taskDescription: '',
    dueDate: new Date(),
    completed: false
  };
  successMessage: string = '';



  
  constructor(private taskService: TaskService, private route: ActivatedRoute) { 
    this.loadTasks();
  }

  
  loadTasks() {
      this.ngOnInit();
      this.taskService.getAllTasks(this.step).subscribe(
        (tasks: Task[]) => {
          this.tasks = tasks;
        },
        (error) => {
          console.error('Error loading tasks:', error);
        }
      );
    }

    ngOnInit() {
      this.route.queryParams.subscribe(params => {this.step = JSON.parse(params['step']);});
     
    }

  openNew() {
    this.productDialog = true;
  }


  addTask(taskForm: NgForm) {
    this.ngOnInit();
    this.taskService.addTask(this.task, this.step).subscribe(
      (response:Task) => {
        console.log(response);
        this.successMessage = 'Task added successfully!';
        taskForm.reset();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error)
      }
    );
  }

  onDeleteTask(task: Task) {
    this.ngOnInit();
    this.taskService.deleteTask(task).subscribe(
      () => {
        console.log('Task deleted successfully.');
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.error('An error occurred while deleting the task:', error);
      }
    );
  }
  
  onUpdateTask(updateForm: NgForm) {
    this.ngOnInit();
    this.taskService.updateTask(this.task).subscribe(
      (updatedTask) => {
        console.log('Task updated successfully:', updatedTask);
        updateForm.reset();
        this.hideDialog();
        window.location.reload();
 
      },
      (error) => {
        console.error('An error occurred while updating the task:', error);
      }
    );
  }


  openTask(task: Task) {
    this.task = { ...task}; 
    this.taskDialog = true;
  }


  hideDialog() {
    this.productDialog = false;
}


showSuccessViaMessages() {
  this.msgs = [];
  this.msgs.push({ severity: 'success', summary: 'Success Message', detail: 'Task added successfully!' });
}

}

