package com.devoteam.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.devoteam.pmo.entity.Task;
import com.devoteam.pmo.service.TaskService;


@RestController
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{stepId}/tasks")
    public List<Task> showTasks(@PathVariable long stepId) throws Exception {
        return taskService.showTasks(stepId);
    }
    
    @GetMapping("/task/{taskId}")
    public Task showTask(@PathVariable long taskId) throws Exception {
        return taskService.showTask (taskId);
    }
    
    

    @PostMapping("/{stepId}/addNewTask")
    public Task addNewTask(@RequestBody Task task, @PathVariable  long stepId) throws Exception {
        return taskService.addNewTask( stepId, task);
    }

    @DeleteMapping("/task/{taskId}/delete")
    public void deleteTask(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/task/{taskId}/update")
    public void updateTask(@RequestBody Task task, @PathVariable long taskId) {
        taskService.update(task, taskId);
    }

}
