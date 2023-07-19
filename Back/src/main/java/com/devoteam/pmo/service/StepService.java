package com.devoteam.pmo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.repository.PhaseRepository;
import com.devoteam.pmo.repository.StepRepository;

@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;
    
    @Autowired
    private PhaseRepository phaseRepository;


    public List<Step> showSteps(long phaseId) throws Exception {
   	 return stepRepository.findByPhasePhaseId(phaseId);
       }

    
    public Step showStep (long stepId) throws Exception {
        Optional<Step> optionalStep = stepRepository.findById(stepId);

        if (optionalStep.isPresent()) {
            return optionalStep.get();
        } else {
            throw new Exception("Step not found for ID: " + stepId);
        }
    }
   public Step addNewStep(Long phaseId, Step newStep) {
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        if (phase != null) {
            newStep.setPhase(phase);
            return stepRepository.save(newStep);
        } else {
            throw new IllegalArgumentException("Phase with ID " + phaseId + " not found.");
        }
    }
   
    public void deleteStep (Step step){
    stepRepository.delete( step);
    }
      public void updateStep(Step step, long id) {
    stepRepository.save(step);
    }


    
}
