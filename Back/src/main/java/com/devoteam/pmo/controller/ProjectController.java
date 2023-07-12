package com.devoteam.pmo.controller;

import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.service.ProjectService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/projects") 
    public List<Project> showProjects(){
     return projectService.showProjects();
    }

    @RequestMapping("/project/{projectId}") 
    public Project showProject(@PathVariable long projectId) throws Exception{
        return projectService.getProjectById(projectId);
    }
    
    
    @PostMapping({"/addNewProject"})
    public Project addNewProject(@RequestBody Project project) {
        return projectService.addNewProject(project);
    }
    @DeleteMapping({"/project/{id}/delete"})
    public void deleteProject(@RequestBody Project project){
     projectService.deleteProject(project);
    }

    @PutMapping({"/project/{projectId}/update"})
    public void updateProject(@RequestBody Project project, @PathVariable long projectId) {
        projectService.updateProject(project, projectId);
    }
    

}
