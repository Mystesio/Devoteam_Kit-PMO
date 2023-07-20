package com.devoteam.pmo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String Taskname;

    private String Taskdescription;

    private Date dueDate;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "Step_id")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private Step step;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskname() {
		return Taskname;
	}

	public void setTaskname(String taskname) {
		Taskname = taskname;
	}

	public String getTaskdescription() {
		return Taskdescription;
	}

	public void setTaskdescription(String taskdescription) {
		Taskdescription = taskdescription;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}