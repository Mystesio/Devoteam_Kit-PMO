import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Step } from '../_model/step.model';
import { Phase } from '../_model/phase.model';

@Injectable({
  providedIn: 'root'
})
export class StepService {
  apiUrl='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }
  
  getStep(step: Step): Observable<any> {
    const url = `${this.apiUrl}/step/${step.stepId}`;
    const options: { headers?: HttpHeaders; params?: HttpParams } = { };
    return this.httpclient.get(url, options);
  }

  public addStep(step: Step , phase: Phase){
    const url = `${this.apiUrl}/${phase.phaseId}/addNewStep`;
    return this.httpclient.post<Step>(url, step);
  }

  public getAllSteps(phase: Phase): Observable<Step[]>{
    return this.httpclient.get<Step[]>(`${this.apiUrl}/${phase.phaseId}/steps`);
  }

  deleteStep(step: Step): Observable<any> {
    const url = `${this.apiUrl}/step/${step.stepId}/delete`;
    const options = { body: step };
    return this.httpclient.delete(url, options);
  }
  updateStep(step : Step) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    const url = `${this.apiUrl}/step/${step.stepId}/update`;
    return this.httpclient.put(url, step, options);
  }
  

}
