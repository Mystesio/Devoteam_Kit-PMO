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
import com.devoteam.pmo.service.PhaseService;


@RestController
public class PhaseController {

 @Autowired
    private PhaseService phaseService;




    @RequestMapping("/phases") 
    public List<Phase> showPhases(){
     return phaseService.showPhase();
    }

    @PostMapping({"/addNewPhase"})
    public Phase addNewPhase(@RequestBody Phase phase) {
    return phaseService.addNewPhase(phase);
    }

    @DeleteMapping({"/phase/{phaseId}/delete"})
    public void deletePhase(@RequestBody Phase phase){
     phaseService.deletePhase(phase);
    }

    @PutMapping({"/phase/{phaseId}/update"})
     public void updatePhase(@RequestBody Phase phase, @PathVariable long phaseId){
     phaseService.update(phase, phaseId);
    }

    
}
