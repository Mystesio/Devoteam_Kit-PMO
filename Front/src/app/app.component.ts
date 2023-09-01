import { Component, OnInit } from '@angular/core';
import { Project } from './_model/project.model';
import { ProjectService } from './_services/project.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'PMO demo';

  projects: Project[] = []; // Initialisez la liste des projets

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.loadProjects(); // Chargez les projets lors de l'initialisation du composant
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe(
      (data) => {
        this.projects = data; // Stockez les données des projets dans la variable
      },
      (error) => {
        console.error('Erreur lors de la récupération des projets :', error);
      }
    );
  }
}
