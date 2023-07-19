package com.devoteam.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.service.StepService;

@RestController
@CrossOrigin
public class StepController {
    @Autowired
    private StepService stepService;

    @GetMapping("/{phaseId}/steps")
    public List<Step> showSteps(@PathVariable long phaseId) throws Exception {
        return stepService.showSteps(phaseId);
    }
    
    @GetMapping("/step/{stepId}")
    public Step showStep(@PathVariable long stepId) throws Exception {
        return stepService.showStep(stepId);
    }
    
    

    @PostMapping("/{phaseId}/addNewStep")
    public Step addNewStep(@RequestBody Step step, @PathVariable  long phaseId) throws Exception {
        return stepService.addNewStep( phaseId, step);
    }

    @DeleteMapping("/step/{stepId}/delete")
    public void deleteStep(@RequestBody Step step) {
        stepService.deleteStep(step);
    }

    @PutMapping("/step/{stepId}/update")
    public void updateStep(@RequestBody Step step, @PathVariable long stepId) {
        stepService.updateStep(step, stepId);
    }


    
}
