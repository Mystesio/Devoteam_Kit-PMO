package com.devoteam.pmo.service;

import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> showProjects() {
    List<Project> projects = new ArrayList<>();
    projectRepository.findAll().forEach(project ->{projects.add(project);});
    return projects;
    } 



    public Project addNewProject (Project project){
       return projectRepository.save(project);
    }

    public void deleteProject (Project project){
    projectRepository.delete(project);
    }

    public void updateProject(Project project, long id) {
    projectRepository.save(project);
    }

    public Project getProjectById(long projectId) throws Exception {
        // Assuming you have a repository or data access object to fetch the project by ID
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        
        // If the project is found, return it; otherwise, you can handle the case as needed
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            // Handle the case when the project is not found (e.g., throw an exception, return null, etc.)
            throw new Exception("Project not found for ID: " + projectId);
        }
    }
    
}
