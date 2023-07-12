package com.devoteam.pmo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.repository.StepRepository;

@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;


     public List<Step> showStep() {
    List<Step> steps = new ArrayList<>();
    stepRepository.findAll().forEach(step ->{steps.add(step);});
    return steps;
    } 

    public Step addNewStep (Step step){
       return stepRepository.save(step);
    }

    public void deleteStep (Step step){
    stepRepository.delete( step);
    }
      public void updateStep(Step step, long id) {
    stepRepository.save(step);
    }


    
}
