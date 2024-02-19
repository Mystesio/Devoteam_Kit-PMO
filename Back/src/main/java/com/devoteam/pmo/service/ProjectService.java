package com.devoteam.pmo.service;

import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> showProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    public Project addNewProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject( long projectId) {
    	projectRepository.deleteById(projectId);
    }

    public void updateProject(Project project, long id) {
        projectRepository.save(project);
    }

    public Project getProjectById(long projectId) throws Exception {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new Exception("Project not found for ID: " + projectId);
        }
    }
}


