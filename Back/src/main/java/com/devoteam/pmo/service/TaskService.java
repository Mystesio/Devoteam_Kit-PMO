package com.devoteam.pmo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devoteam.pmo.entity.Task;
import com.devoteam.pmo.repository.TaskRepository;

@Service
public class TaskService {

      @Autowired
    private TaskRepository taskRepository;


    public List<Task> showTask() {
    List<Task> tasks = new ArrayList<>();
    taskRepository.findAll().forEach(task ->{tasks.add(task);});
       return tasks;
    }

    public Task addNewTask (Task task){
       return taskRepository.save(task);
    }

    public void deleteTask (Task task){
       taskRepository.delete(task);
    }
    public void update(Task task, long id) {
       taskRepository.save(task);
    }

    
}
