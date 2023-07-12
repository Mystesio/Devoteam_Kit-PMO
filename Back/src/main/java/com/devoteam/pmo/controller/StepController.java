package com.devoteam.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.service.StepService;

@RestController
@CrossOrigin
public class StepController {
    @Autowired
    private StepService stepService;

      @RequestMapping("/steps") 
    public List<Step> showSteps(){
     return stepService.showStep();
    }

    @PostMapping({"/addNewStep"})
    public Step addNewPhase(@RequestBody Step step) {
        return stepService.addNewStep(step);
    }
    @DeleteMapping({"/deletStep"})
    public void deleteProject(@RequestBody Step step){
     stepService.deleteStep(step);
    }

    
}
