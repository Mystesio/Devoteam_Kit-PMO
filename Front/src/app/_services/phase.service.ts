import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Phase } from '../_model/phase.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhaseService {
 
  apiUrl='http://localhost:8086';

  constructor(private httpclient: HttpClient) { }

  public addPhase(phase: Phase, projectId: string) {
    const url = `${this.apiUrl}/addNewPhase/${projectId}`;
    return this.httpclient.post<Phase>(url, phase);
  }

  public getAllPhases(projectId: string): Observable<Phase[]>{
    return this.httpclient.get<Phase[]>(`${this.apiUrl}/phases/${projectId}`);

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
