package com.devoteam.pmo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.PhaseRepository;
import com.devoteam.pmo.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service

public class PhaseService {

@Autowired
private PhaseRepository phaseRepository;

@Autowired
private ProjectRepository projectRepository;





public List<Phase> showPhases(long projectId) throws Exception {
	
	 return phaseRepository.findByProjectProjectId(projectId);
      
    }
public Phase showPhase (long phaseId) throws Exception {
    Optional<Phase> optionalPhase = phaseRepository.findById(phaseId);

    if (optionalPhase.isPresent()) {
        return optionalPhase.get();
    } else {
        throw new Exception("Phase not found for ID: " + phaseId);
    }
}

public Phase addNewPhase(Long projectId, Phase newPhase) {
    Project project = projectRepository.findById(projectId).orElse(null);
    if (project != null) {
        newPhase.setProject(project);
        return phaseRepository.save(newPhase);
    } else {
        throw new IllegalArgumentException("Project with ID " + projectId + " not found.");
    }
}


    public void deletePhase(Phase phase) {
        phaseRepository.delete(phase);
    }

    public void update(Phase phase, long id) {
          phaseRepository.save(phase);
    }
    
}
