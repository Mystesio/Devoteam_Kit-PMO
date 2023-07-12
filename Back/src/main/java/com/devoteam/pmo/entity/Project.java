package com.devoteam.pmo.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Data
@Entity

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

}



