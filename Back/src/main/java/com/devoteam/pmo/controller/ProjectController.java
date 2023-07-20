package com.devoteam.pmo.controller;

import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.service.ProjectService;

import io.swagger.annotations.ApiOperation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
     
    @ApiOperation(value = "Show  all projects")
    @GetMapping("/projects") 
    public List<Project> showProjects(){
     return projectService.showProjects();
    }
    @ApiOperation(value = "Show a project")
    @GetMapping("/project/{projectId}") 
    public Project showProject(@PathVariable long projectId) throws Exception{
        return projectService.getProjectById(projectId);
    }
    
    @ApiOperation(value = "add new project")
    @PostMapping({"/addNewProject"})
    public Project addNewProject(@RequestBody Project project) {
        return projectService.addNewProject(project);
    }
    @ApiOperation(value = "delete a project")
    @DeleteMapping({"/project/{id}/delete"})
    public void deleteProject(@PathVariable long projectId){
     projectService.deleteProject(projectId);
    }
    @ApiOperation(value = "update a project")
    @PutMapping({"/project/{projectId}/update"})
    public void updateProject(@RequestBody Project project, @PathVariable long projectId) {
        projectService.updateProject(project, projectId);
    }
    

}
