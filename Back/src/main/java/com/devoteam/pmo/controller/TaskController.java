package com.devoteam.pmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devoteam.pmo.entity.Task;
import com.devoteam.pmo.service.TaskService;


@RestController
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService taskService;

      @RequestMapping("/tasks") 
    public List<Task> showTasks(){
     return taskService.showTask();
    }

    @PostMapping({"/addNewTask"})
    public Task addNewTask(@RequestBody Task task) {
        return taskService.addNewTask(task);
    }
    @DeleteMapping({"/deleteTask"})
    public void deleteTask(@RequestBody Task task){
     taskService.deleteTask(task);
    }


}
