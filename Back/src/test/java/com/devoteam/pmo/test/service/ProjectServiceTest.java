package com.devoteam.pmo.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.ProjectRepository;
import com.devoteam.pmo.service.ProjectService;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowProjects() {
        // Arrange
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        when(projectRepository.findAll()).thenReturn(projects);

        // Act
        List<Project> result = projectService.showProjects();

        // Assert
        assertEquals(projects, result);
    }

    @Test
    public void testAddNewProject() {
        // Arrange
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        // Act
        Project result = projectService.addNewProject(project);

        // Assert
        assertEquals(project, result);
    }

    @Test
    public void testDeleteProject() {
        // Arrange
        Project project = new Project();

        // Act
        projectService.deleteProject(project);

        // Assert
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    public void testUpdateProject() {
        // Arrange
        long projectId = 1L;
        Project project = new Project();

        // Act
        projectService.updateProject(project, projectId);

        // Assert
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testGetProjectByIdExisting() throws Exception {
        // Arrange
        long projectId = 1L;
        Project project = new Project();
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Act
        Project result = projectService.getProjectById(projectId);

        // Assert
        assertEquals(project, result);
    }

    @Test
    public void testGetProjectByIdNonExisting() {
        // Arrange
        long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> projectService.getProjectById(projectId));
    }
}
