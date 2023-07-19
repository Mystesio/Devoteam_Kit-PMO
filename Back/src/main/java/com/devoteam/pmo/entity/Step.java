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

    // Constructors, getters, setters, and other methods
}