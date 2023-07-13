package com.devoteam.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.service.PhaseService;
import com.devoteam.pmo.service.ProjectService;


@RestController
public class PhaseController {

 @Autowired
    private PhaseService phaseService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/phases")
    public List<Phase> showPhases() {
        return phaseService.showPhase();
    }

    @PostMapping("/addNewPhase/{projectId}")
public Phase addNewPhase(@RequestBody Phase phase, @PathVariable("projectId") Long projectId) throws Exception {
    Project project = projectService.getProjectById(projectId); // Assuming there is a projectService to retrieve the project
    return phaseService.addNewPhase(phase, project);
}

    @DeleteMapping("/phase/{phaseId}/delete")
    public void deletePhase(@RequestBody Phase phase) {
        phaseService.deletePhase(phase);
    }

    @PutMapping("/phase/{phaseId}/update")
    public void updatePhase(@RequestBody Phase phase, @PathVariable long phaseId) {
        phaseService.update(phase, phaseId);
    }

    
}
