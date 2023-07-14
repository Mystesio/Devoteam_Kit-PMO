package com.devoteam.pmo.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@Data
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
    private List<Step> steps;

}
