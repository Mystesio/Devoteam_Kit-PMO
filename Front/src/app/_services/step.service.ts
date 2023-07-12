import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Step } from '../_model/step.model';

@Injectable({
  providedIn: 'root'
})
export class StepService {
  PATH_OF_API='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }


  public addStep(step: Step){
    return this.httpclient.post<Step>("http://localhost:8086/addNewStep", step);
  }


  public getAllTasks(): Observable<Step[]>{
    return this.httpclient.get<Step[]>(`${this.PATH_OF_API}/steps`);

  }


}
