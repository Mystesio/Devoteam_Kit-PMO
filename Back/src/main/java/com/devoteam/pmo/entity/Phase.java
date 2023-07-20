package com.devoteam.pmo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Phase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phaseId;
  
    private String PhaseName;

    private Date startDate;

	private Date endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phase")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private List<Step> steps;

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public String getPhaseName() {
		return PhaseName;
	}

	public void setPhaseName(String phaseName) {
		PhaseName = phaseName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
  
}
