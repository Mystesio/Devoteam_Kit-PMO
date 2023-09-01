package com.devoteam.pmo.test.controller;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devoteam.pmo.controller.TaskController;
import com.devoteam.pmo.entity.Task;
import com.devoteam.pmo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testShowTasks() throws Exception {
        // Arrange
        long stepId = 1;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        when(taskService.showTasks(stepId)).thenReturn(tasks);

        // Act & Assert
        mockMvc.perform(get("/" + stepId + "/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tasks.size()));
    }

    @Test
    public void testShowTask() throws Exception {
        // Arrange
        long taskId = 1;
        Task task = new Task();
        when(taskService.showTask(taskId)).thenReturn(task);

        // Act & Assert
        mockMvc.perform(get("/task/" + taskId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNewTask() throws Exception {
        // Arrange
        long stepId = 1L;
        Task task = new Task(); // Replace this with the actual Task object initialization
        when(taskService.addNewTask(stepId, task)).thenReturn(task);

        // Act & Assert
        mockMvc.perform(post("/" + stepId + "/addNewTask")
                .contentType("application/json")
                .content("{\r\n"
                        + "  \"taskId\": 0,\r\n"
                        + "  \"taskName\": \"Task Name\"\r\n"
                        + "}\r\n"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTask() throws Exception {
        // Arrange
        long taskId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/task/" + taskId + "/delete"))
                .andExpect(status().isOk());

        // Verify that the deleteTask method of the TaskService is called once with the correct taskId
        verify(taskService, times(1)).deleteTask(taskId);
    }

    @Test
    public void testUpdateTask() throws Exception {
        long taskId = 1L;
        Task updatedTask = new Task();
        updatedTask.setTaskId(taskId);
        updatedTask.setTaskName("Task Update");

        // Set up the mock taskService
        Mockito.doNothing().when(taskService).update(any(Task.class), eq(taskId));

        // Act & Assert
        mockMvc.perform(put("/task/" + taskId + "/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk());

        // Verify that the update method of the TaskService is called once with the correct task and taskId
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskService, times(1)).update(taskCaptor.capture(), eq(taskId));

        // Compare the properties of the captured Task with the expected properties
        Task capturedTask = taskCaptor.getValue();
        assertEquals(updatedTask.getTaskName(), capturedTask.getTaskName());
    }
}

