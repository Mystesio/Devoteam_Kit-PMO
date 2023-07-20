package com.devoteam.pmo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Getter
@Setter
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stepId;

    private String Stepname;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "Phase_id")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private Phase phase;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "step")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private List<Task> tasks;

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public String getStepname() {
		return Stepname;
	}

	public void setStepname(String stepname) {
		Stepname = stepname;
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

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

    
    // Constructors, getters, setters, and other methods
}