package com.devoteam.pmo.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.repository.PhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service

public class PhaseService {

@Autowired
private PhaseRepository phaseRepository;
 
   public List<Phase> showPhase() {
    List<Phase> phases = new ArrayList<>();
    phaseRepository.findAll().forEach(phase ->{phases.add(phase);});
    return phases;
    } 

    public Phase addNewPhase (Phase phase){
       return phaseRepository.save(phase);
    }

    public void deletePhase (Phase phase){
    phaseRepository.delete(phase);
    }
     public void update(Phase phase, long id) {
    phaseRepository.save(phase);
    }

    
}
