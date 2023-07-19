package com.devoteam.pmo.repository;

import com.devoteam.pmo.entity.Step;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface StepRepository  extends JpaRepository<Step, Long> {

	 List<Step> findByPhasePhaseId(long phaseId);
    
}
