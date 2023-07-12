package com.devoteam.pmo.repository;

import com.devoteam.pmo.entity.Step;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface StepRepository  extends JpaRepository<Step, Long> {
    
}
