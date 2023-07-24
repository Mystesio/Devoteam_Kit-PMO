import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Phase } from '../_model/phase.model';
import { Observable } from 'rxjs';
import { Project } from '../_model/project.model';

@Injectable({
  providedIn: 'root'
})
export class PhaseService {
 
  apiUrl='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }

  getPhase(phase: Phase): Observable<any> {
    const url = `${this.apiUrl}/phase/${phase.phaseId}`;
    const options: { headers?: HttpHeaders; params?: HttpParams } = { };
    return this.httpclient.get(url, options);
  }

  public addPhase(phase: Phase , project: Project){
    const url = `${this.apiUrl}/${project.projectId}/addNewPhase`;
    return this.httpclient.post<Phase>(url, phase);
  }

  public getAllPhases(project: Project): Observable<Phase[]>{
    return this.httpclient.get<Phase[]>(`${this.apiUrl}/${project.projectId}/phases`);
  }

  deletePhase(phase: Phase): Observable<any> {
    const url = `${this.apiUrl}/phase/${phase.phaseId}/delete`;
    const options = { body: phase };
    return this.httpclient.delete(url, options);
  }
  updatePhase(phase : Phase) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    const url = `${this.apiUrl}/phase/${phase.phaseId}/update`;
    return this.httpclient.put(url, phase, options);
  }
  

}
