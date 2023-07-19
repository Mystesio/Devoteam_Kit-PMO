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

}