package com.devoteam.pmo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.entity.Task;
import com.devoteam.pmo.repository.StepRepository;
import com.devoteam.pmo.repository.TaskRepository;

@Service
public class TaskService {

      @Autowired
    private TaskRepository taskRepository;
      
      @Autowired
      private StepRepository stepRepository;


      public List<Task> showTasks(long stepId) throws Exception {
    	   	 return taskRepository.findByStepStepId(stepId);
    	       }

      public Task showTask (long taskId) throws Exception {
          Optional<Task> optionalTask = taskRepository.findById(taskId);

          if (optionalTask.isPresent()) {
              return optionalTask.get();
          } else {
              throw new Exception("Task not found for ID: " + taskId);
          }
      }
     public Task addNewTask(Long stepId, Task newTask) {
          Step step = stepRepository.findById(stepId).orElse(null);
          if (step != null) {
              newTask.setStep(step);
              return taskRepository.save(newTask);
          } else {
              throw new IllegalArgumentException("Phase with ID " + stepId + " not found.");
          }
      }

    public void deleteTask (Task task){
       taskRepository.delete(task);
    }
    public void update(Task task, long id) {
       taskRepository.save(task);
    }

    
}
