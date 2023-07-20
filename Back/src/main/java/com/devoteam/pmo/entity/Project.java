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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private String sponsor;
    private String domain;
    private String nature;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private List<Phase> phases;
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
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
	public List<Phase> getPhases() {
		return phases;
	}
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}


}



