import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Message, MessageService } from 'primeng/api';
import { Task} from 'src/app/_model/task.model';
import { TaskService } from 'src/app/_services/task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss'],
  providers: [MessageService]
})
export class AddTaskComponent {
  productDialog: boolean = false;
  taskForm: FormGroup | undefined;

  value5: any;


  task: Task = {
    Taskname: '',
    TaskDescription: '',
    dueDate:new Date(), 
    completed: false
  };
  successMessage: string = '';
  msgs: Message[] = [];
  constructor(private taskService: TaskService) { }


  ngOnInit(): void {

  }

  openNew() {
    this.productDialog = true;
  }


  addTask(taskForm: NgForm) {
    this.taskService.addTask(this.task).subscribe(
      (response: Task) => {
        console.log(response);
        this.successMessage = 'task added successfully!';
        taskForm.reset();


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
