package com.devoteam.pmo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Taskname;

    private String Taskdescription;

    private Date dueDate;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;

}