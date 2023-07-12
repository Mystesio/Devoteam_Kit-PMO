package com.devoteam.pmo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Stepname;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "step")
    private List<Task> tasks;

    // Constructors, getters, setters, and other methods
}