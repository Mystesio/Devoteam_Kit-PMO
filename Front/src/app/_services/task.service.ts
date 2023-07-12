import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../_model/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  PATH_OF_API='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }


  public addTask(task: Task){
    return this.httpclient.post<Task>("http://localhost:8086/addNewTask", task);
  }


  public getAllTasks(): Observable<Task[]>{
    return this.httpclient.get<Task[]>(`${this.PATH_OF_API}/Tasks`);

  }


}
