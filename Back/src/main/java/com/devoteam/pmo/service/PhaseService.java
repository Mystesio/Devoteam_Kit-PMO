package com.devoteam.pmo.service;

import java.util.List;


import org.springframework.stereotype.Service;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service

public class PhaseService {

@Autowired
private PhaseRepository phaseRepository;

@Autowired
private ProjectService projectService;

public List<Phase> showPhase() {
        return phaseRepository.findAll();
    }
    public Phase addNewPhase(Phase phase) {
        Project project = phase.getProject();
    
        if (project != null) {
            // Save the project if it's not already saved
            if (project.getProjectId() ==  phase.getProject().getProjectId()) {
                projectService.addNewProject(project);
            }
        } else {
            // Handle the case where phase.getProject() returns null
            // You can either throw an exception or handle it in an appropriate way based on your requirements
        }
    
        return phaseRepository.save(phase);
    }
    
    public void deletePhase(Phase phase) {
        phaseRepository.delete(phase);
    }

    public void update(Phase phase, long id) {
        // Ensure that the phase object has the correct ID before saving
        phase.setPhaseId(id);

        // Set the project for the phase
        Project project = phase.getProject();
        phase.setProject(project);

        // Set the phase for each step (assuming steps exist)
        phase.getSteps().forEach(step -> step.setPhase(phase));

        phaseRepository.save(phase);
    }
    
}
