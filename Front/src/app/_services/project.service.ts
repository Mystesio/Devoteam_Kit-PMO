import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../_model/project.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProjectService {

  private apiUrl = 'http://localhost:8086';
  constructor(private httpClient: HttpClient) { }

  
  getProject(project: Project): Observable<any> {
    const url = `${this.apiUrl}/project/${project.projectId}`;
    const options: { headers?: HttpHeaders; params?: HttpParams } = { }; // Update the type of options object
    return this.httpClient.get(url, options);
  }
  
  addProject(project: Project): Observable<Project> {
    const url = `${this.apiUrl}/addNewProject`;
    return this.httpClient.post<Project>(url, project);
  }

  getAllProjects(): Observable<Project[]> {
    const url = `${this.apiUrl}/projects`;
    return this.httpClient.get<Project[]>(url);
  }

  deleteProject(project: Project): Observable<any> {
    const url = `${this.apiUrl}/project/${project.projectId}/delete`;
    const options = { body: project };
    return this.httpClient.delete(url, options);
  }
  updateProject(project: Project) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const options = { headers: headers };
    const url = `${this.apiUrl}/project/${project.projectId}/update`;
    return this.httpClient.put(url, project, options);
  }
  
}
