package com.devoteam.pmo.test.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devoteam.pmo.controller.ProjectController;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testShowProjects() throws Exception {
        // Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        when(projectService.showProjects()).thenReturn(projects);

        // Act & Assert
        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(projects.size()));
    }

    @Test
    public void testShowProject() throws Exception {
        // Arrange
        long projectId = 1L;
        Project project = new Project();
        when(projectService.getProjectById(projectId)).thenReturn(project);

        // Act & Assert
        mockMvc.perform(get("/project/" + projectId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNewProject() throws Exception {
        // Arrange
        Project project = new Project();
        // Set up any necessary properties of the project object for the test

        when(projectService.addNewProject(any(Project.class))).thenReturn(project);

        // Act & Assert
        mockMvc.perform(post("/addNewProject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProject() throws Exception {
        // Arrange
        long projectId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/project/" + projectId + "/delete"))
                .andExpect(status().isOk());

        // Verify that the deleteProject method of the ProjectService is called once with the correct projectId
        verify(projectService, times(1)).deleteProject(projectId);
    }

    @Test
    public void testUpdateProject() throws Exception {
        long projectId = 1L;
        Project updatedProject = new Project();
        updatedProject.setProjectId(projectId);
        updatedProject.setProjectName("Project Update");
        // Set up any other necessary properties of the updated project

        // Set up the mock projectService
        Mockito.doNothing().when(projectService).updateProject(any(Project.class), eq(projectId));

        // Act & Assert
        mockMvc.perform(put("/project/" + projectId + "/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProject)))
                .andExpect(status().isOk());

        // Verify that the updateProject method of the ProjectService is called once with the correct project and projectId
        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectService, times(1)).updateProject(projectCaptor.capture(), eq(projectId));

        // Compare the properties of the captured Project with the expected properties
        Project capturedProject = projectCaptor.getValue();
        assertEquals(updatedProject.getProjectName(), capturedProject.getProjectName());
        // Compare any other relevant properties
    }
}

