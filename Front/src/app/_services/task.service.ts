import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../_model/task.model';
import { Step } from '../_model/step.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  apiUrl='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }

  getTask(task: Task): Observable<any> {
    const url = `${this.apiUrl}/task/${task.taskId}`;
    const options: { headers?: HttpHeaders; params?: HttpParams } = { };
    return this.httpclient.get(url, options);
  }

  public addTask(task: Task , step: Step){
    const url = `${this.apiUrl}/${step.stepId}/addNewTask`;
    return this.httpclient.post<Task>(url, task);
  }

  public getAllTasks(step: Step): Observable<Task[]>{
    return this.httpclient.get<Task[]>(`${this.apiUrl}/${step.stepId}/tasks`);
  }

  deleteTask(task: Task): Observable<any> {
    const url = `${this.apiUrl}/task/${task.taskId}/delete`;
    const options = { body: task };
    return this.httpclient.delete(url, options);
  }
  
  updateTask(task : Task) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    const url = `${this.apiUrl}/task/${task.taskId}/update`;
    return this.httpclient.put(url, task, options);
  }
  

}
