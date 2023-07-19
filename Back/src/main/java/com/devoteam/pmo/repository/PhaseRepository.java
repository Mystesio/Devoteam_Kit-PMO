package com.devoteam.pmo.repository;

import com.devoteam.pmo.entity.Phase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhaseRepository  extends JpaRepository <Phase, Long> {

	List<Phase> findByProjectProjectId(long projectId);
    
}
